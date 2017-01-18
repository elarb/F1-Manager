package edu.tudelft.games.f1manager.game;

import edu.tudelft.games.f1manager.core.*;
import edu.tudelft.games.f1manager.tools.RandomDouble;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    private DriverList drivers;
    private AiTeamList aiteams;
    private PlayerTeam playerteam;
    private Season season;
    private GameEvents events;
    private Comparator<DriverResult> byTime = Comparator.comparingDouble(DriverResult::getTime);
    private Comparator<Team> byPoints = (e1, e2) -> Double.compare(e2.getPoints(), e1.getPoints());

    /**
     * Method that creates a new instance of a game and reads a json files from a previous save
     *
     * @param savename name of the save you want to load
     * @return the new game instance.
     */
    public static Game loadgame(String savename) {

        DriverList driverList = DriverList.read(savename + "/drivers.json");
        AiTeamList aiTeamList = AiTeamList.read(savename + "/aiteams.json");
        PlayerTeam playerTeam = PlayerTeam.read(savename + "/playerteam.json");
        GameEvents events = GameEvents.read(savename + "/events.json");
        Season season = Season.read(savename + "/season.json");

        Game game = new Game();

        game.setDrivers(driverList);
        game.setAiteams(aiTeamList);
        game.setPlayerteam(playerTeam);
        game.setEvents(events);
        game.setSeason(season);
        System.out.println("Succesfully loaded your game!");

        return game;
    }

    /**
     * Method that creates a new instance of a game.
     *
     * @return the new game instance
     */
    public static Game newGame() {
        DriverList driverList = DriverList.read("drivers.json");
        AiTeamList aiTeamList = AiTeamList.read("aiteams.json");
        PlayerTeam playerTeam = PlayerTeam.read("playerteam.json");
        Season season = Season.read("season.json");
        GameEvents events = GameEvents.read("events.json");

        Game game = new Game();

        game.setDrivers(driverList);
        game.setAiteams(aiTeamList);
        game.setPlayerteam(playerTeam);
        game.setEvents(events);
        game.setSeason(season);

        System.out.println("Succesfully created a new game!");

        return game;
    }

    /**
     * Method that saves your current game instance to all json files.
     *
     * @param savename the name you want to give to the save
     * @throws IOException if file does not exists
     */
    public void savegame(String savename) throws IOException {

        File dir = new File("src/main/resources/JSON/" + savename);
        dir.mkdir();

        this.drivers.write(savename + "/drivers.json");
        this.aiteams.write(savename + "/aiteams.json");
        this.playerteam.write(savename + "/playerteam.json");
        this.season.write(savename + "/season.json");
        this.events.write(savename + "/events.json");

        System.out.println("Succesfully saved your game: " + savename);

    }

    /**
     * Methods that gets runned when a race gets started.
     */
    public void race() {
        balanceDrivers();
        setTeamIDs();
        handleResults();
        buyRandomDriver();
        sortResults();
        attributepointsandbudget();
        updateStandings();
        this.getSeason().nextRace();
    }


    /**
     * Helper method for race.
     *
     * @param team for every driver in team, their
     *             result of the race gets added to the list of driverresults
     */
    public void addDriverResults(Team team) {
        Driver driver1 = team.getDriverList().get(0);
        Driver driver2 = team.getDriverList().get(1);
        driver1.determineValue();
        driver2.determineValue();
        Engine engine = team.getCar().getEngine();
        engine.determineprice();
        DriverResult result1 = new DriverResult(driver1,
                ((this.getSeason().getCurrentRaceInstance().getCircuit().getRaceTimeBase() * (Constants.VALUE_AVG_RESULT - team.getResultsDriver1())) / Constants.VALUE_AVG_DIVIDER) + team.getMechanic().getPitstopTime());
        DriverResult result2 = new DriverResult(driver2,
                ((this.getSeason().getCurrentRaceInstance().getCircuit().getRaceTimeBase() * (Constants.VALUE_AVG_RESULT - team.getResultsDriver2())) / Constants.VALUE_AVG_DIVIDER) + team.getMechanic().getPitstopTime());
        this.getSeason().getCurrentRaceInstance().getResults().addAll(Arrays.asList(result1, result2));
    }

    /**
     * traverses through the list of aiteams  and playerteam
     * and adds the results of their drivers to the results of the current race.
     */
    public void handleResults() {
        for (AiTeam team :
                this.aiteams.getTeams()) {
            addDriverResults(team);
        }
        addDriverResults(this.playerteam);
    }

//  /**
//   * Returns the calculated factor of the current race.
//   *
//   * @return the calculated factor of the current race
//   */
//  public double getCurrentRaceFactor() {
//    //TODO by Tim: Add and tweak formula
//    double somethingFactor = 10;
//    double elseFactor = 30;
//    double fooFactor = 40;
//    return somethingFactor * elseFactor * fooFactor;
//  }

    public void setTeamIDs() {

        for (AiTeam team : this.aiteams.getTeams()) {
            for (Driver driver : team.getDriverList()) {
                driver.setTeamId(team.getId());
            }
        }
        for (Driver playerdriver : this.playerteam.getDriverList()) {
            playerdriver.setTeamId(this.playerteam.getId());
        }

    }

    /**
     * A playerteam Driver Buy method.
     *
     * @param driver the driver the playerteam buys
     * @return true if the buy is successful, false otherwise
     */
    public boolean driverBuy(Driver driver) {
        int budget = this.getPlayerteam().getBudget();
        double random = RandomDouble.generatePercentage();

        if (random < 70 && budget > driver.getValue()) {
            this.playerteam.addDriver(driver);
            this.playerteam.setBudget(budget - driver.getValue());
            String msg = driver.getName() + " has been purchased by you!";
            GameEvent event = new GameEvent(msg, GameEvent.Type.TRANSFER);
            this.events.addEvent(event);
            return true;
        }
        return false;
    }

    /**
     * Makes sure all aiteams have 2 drivers before a race starts.
     */
    public void balanceDrivers() {
        for (int i = 0; i < this.getAiteams().size(); i++) {
            if (this.getAiteams().get(i).getDriverList().size() < 2) {
                buyLeftoverDriver(this.getAiteams().get(i));
            }
        }
    }

    /**
     * Helper method for balanceDrivers.
     *
     * @param aiTeam that buys a random driver without a team
     */
    public void buyLeftoverDriver(AiTeam aiTeam) {
        Random rand = new Random();
        ArrayList<Driver> list = this.getDrivers();
        while (true) {
            Driver driver = list.get(rand.nextInt(list.size()));
            if (driver.getTeamId() == 0) {
                aiBuy(aiTeam, driver);
                break;
            }
        }
    }

    /**
     * Random Aiteams buy random drivers.
     */
    public void buyRandomDriver() {
        Random rand = new Random();
        int random = rand.nextInt(Constants.MAX_BUYS);
        for (int i = 0; i < random; i++) {
            AiTeam randomTeam = this.getAiteams().get(new Random().nextInt(this.getAiteams().size()));
            Driver randomDriver = this.getDrivers().get(new Random().nextInt(this.getDrivers().size()));
            aiBuy(randomTeam, randomDriver);
        }
    }

    /**
     * Aiteam driver buy.
     *
     * @param team   the aiteam that buys a driver
     * @param driver the driver that gets bought
     */
    public void aiBuy(AiTeam team, Driver driver) {
        String msg = String.format("%s has been purchased by %s", driver.getName(), team.getName());

        if (driver.getTeamId() == 1) {
            playerteam.addBudget(driver.getValue());
            msg = String.format("%s has been purchased by %s, your balance has increased by $ %s", driver.getName(), team.getName(), driver.getValue());
        }
        team.getDriverList().add(driver);
        driver.setTeamId(team.getId());

        GameEvent event = new GameEvent(msg, GameEvent.Type.TRANSFER);
        this.events.addEvent(event);
        //adds this event to the list of events

    }

    public void sortResults() {

        getResults()
                .stream()
                .sorted(byTime)
                .forEach(System.out::println);

        System.out.println();
        System.out.println();
        System.out.println();


    }


    public void attributepointsandbudget() {

        ArrayList<DriverResult> ordered = getResults().stream().sorted(byTime).collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < 10; i++) {

            if (getTeamDriver(ordered.get(i).getDriver().getTeamId()) instanceof PlayerTeam) {

                PlayerTeam playerTeam = (PlayerTeam) getTeamDriver(ordered.get(i).getDriver().getTeamId());

                if (i == 0) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 25);
                    playerTeam.setBudget(playerTeam.getBudget() + 25 * 2000000);
                }
                if (i == 1) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 18);
                    playerTeam.setBudget(playerTeam.getBudget() + 18 * 2000000);
                }
                if (i == 2) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 15);
                    playerTeam.setBudget(playerTeam.getBudget() + 15 * 2000000);
                }
                if (i == 3) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 12);
                    playerTeam.setBudget(playerTeam.getBudget() + 12 * 2000000);
                }
                if (i == 4) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 10);
                    playerTeam.setBudget(playerTeam.getBudget() + 10 * 2000000);
                }
                if (i == 5) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 8);
                    playerTeam.setBudget(playerTeam.getBudget() + 8 * 2000000);
                }
                if (i == 6) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 6);
                    playerTeam.setBudget(playerTeam.getBudget() + 6 * 2000000);
                }
                if (i == 7) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 4);
                    playerTeam.setBudget(playerTeam.getBudget() + 4 * 2000000);
                }
                if (i == 8) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 2);
                    playerTeam.setBudget(playerTeam.getBudget() + 2 * 2000000);
                }
                if (i == 9) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 1);
                    playerTeam.setBudget(playerTeam.getBudget() + 1 * 2000000);
                }

            } else {
                if (i == 0) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 25);
                }
                if (i == 1) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 18);
                }
                if (i == 2) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 15);
                }
                if (i == 3) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 12);
                }
                if (i == 4) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 10);
                }
                if (i == 5) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 8);
                }
                if (i == 6) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 6);
                }
                if (i == 7) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 4);
                }
                if (i == 8) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 2);
                }
                if (i == 9) {
                    getTeamDriver(ordered.get(i).getDriver().getTeamId()).setPoints(getTeamDriver(ordered.get(i).getDriver().getTeamId()).getPoints() + 1);
                }

            }

        }

    }

    public Team getTeamDriver(int id) {

        for (AiTeam team : this.getAiteams()) {

            if (team.getId() == id) {
                return team;
            }

        }
        return playerteam;

    }

    public void updateStandings() {

        ArrayList<Team> teams = new ArrayList<Team>();

        teams.addAll(this.getAiteams());
        teams.add(this.getPlayerteam());

        ArrayList<Team> standings = teams.stream().sorted(byPoints).collect(Collectors.toCollection(ArrayList::new));

        this.getSeason().setStandings(standings);

    }


    public ArrayList<Driver> getDrivers() {
        return drivers.getDrivers();
    }

    public void setDrivers(DriverList drivers) {
        this.drivers = drivers;
    }

    public ArrayList<AiTeam> getAiteams() {
        return aiteams.getTeams();
    }

    public void setAiteams(AiTeamList aiteams) {
        this.aiteams = aiteams;
    }

    public PlayerTeam getPlayerteam() {
        return playerteam;
    }

    public void setPlayerteam(PlayerTeam playerteam) {
        this.playerteam = playerteam;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public GameEvents getEvents() {
        return events;
    }

    public void setEvents(GameEvents events) {
        this.events = events;
    }

    public int getCurrentRace() {
        return this.getSeason().getCurrentRace();
    }

    public ArrayList<DriverResult> getResults() {
        return this.season.getCurrentRaceInstance().getResults();
    }

    public double getCurrentBaseTime() {
        return this.getSeason().getCurrentRaceInstance().getCircuit().getRaceTimeBase();
    }

}
