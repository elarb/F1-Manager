package edu.tudelft.games.f1manager.game;

import edu.tudelft.games.f1manager.core.Aerodynamicist;
import edu.tudelft.games.f1manager.core.AiTeam;
import edu.tudelft.games.f1manager.core.AiTeamList;
import edu.tudelft.games.f1manager.core.Constants;
import edu.tudelft.games.f1manager.core.Driver;
import edu.tudelft.games.f1manager.core.DriverList;
import edu.tudelft.games.f1manager.core.Engine;
import edu.tudelft.games.f1manager.core.EngineList;
import edu.tudelft.games.f1manager.core.Mechanic;
import edu.tudelft.games.f1manager.core.PlayerTeam;
import edu.tudelft.games.f1manager.core.Strategist;
import edu.tudelft.games.f1manager.core.Team;

import edu.tudelft.games.f1manager.tools.RandomDouble;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {

  private DriverList drivers;
  private EngineList engines;
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
    EngineList engineList = EngineList.read("engines.json");
    AiTeamList aiTeamList = AiTeamList.read(savename + "/aiteams.json");
    PlayerTeam playerTeam = PlayerTeam.read(savename + "/playerteam.json");
    GameEvents events = GameEvents.read(savename + "/events.json");
    Season season = Season.read(savename + "/season.json");
    Game game = new Game();

    game.setDrivers(driverList);
    game.setEngines(engineList);
    game.setAiteams(aiTeamList);
    game.setEngines(engineList);
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
    EngineList engineList = EngineList.read("engines.json");
    AiTeamList aiTeamList = AiTeamList.read("aiteams.json");
    PlayerTeam playerTeam = PlayerTeam.read("playerteam.json");
    Season season = Season.read("season.json");
    GameEvents events = GameEvents.read("events.json");

    Game game = new Game();

    game.setDrivers(driverList);
    game.setEngines(engineList);
    game.setAiteams(aiTeamList);
    game.setEngines(engineList);
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
    //TODO: ENGINE LIST WRITE
    this.playerteam.write(savename + "/playerteam.json");
    this.season.write(savename + "/season.json");
    this.events.write(savename + "/events.json");

    System.out.println("Succesfully saved your game: " + savename);

  }

  /**
   * Methods that gets runned when a race gets started.
   * Returns true if there is a next race and if the playerteam
   * has enough drivers.
   *
   * @return true if there is a next race and if the playerteam has enough drivers
   */
  public boolean race() {
    if (this.getSeason().getCurrentRace() < 20 && playerteam.enoughDrivers()) {
      balanceDrivers();
      setTeamIDs();
      payRace();
      handleResults();
      sortResults();
      addRaceWinnings();
      updateStandings();
      buyRandomDriver();
      gameEventPositions();
      gameEventCrashed();
      this.getSeason().nextRace();
      return true;
    } else if (getSeason().getCurrentRace() == 20) {
      balanceDrivers();
      setTeamIDs();
      payRace();
      handleResults();
      sortResults();
      addRaceWinnings();
      championAward();
      updateStandings();
      buyRandomDriver();
      gameEventPositions();
      gameEventCrashed();
      return false;
    }
    return false;
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

    DriverResult result1 = new DriverResult(driver1, ((this.getSeason()
        .getCurrentRaceInstance().getCircuit().getRaceTimeBase()
        * (Constants.VALUE_AVG_RESULT - team.getResultsDriver1())) / Constants.VALUE_AVG_DIVIDER)
        + team.getMechanic().getPitstopTime());
    DriverResult result2 = new DriverResult(driver2, ((this.getSeason().getCurrentRaceInstance()
        .getCircuit().getRaceTimeBase() * (Constants.VALUE_AVG_RESULT
        - team.getResultsDriver2())) / Constants.VALUE_AVG_DIVIDER)
        + team.getMechanic().getPitstopTime());

    if (team.getStrategist().hasCrashed()) {

      if (RandomDouble.generate(0, 1) > 0.1) {

        if (RandomDouble.generate(0, 1) > 0.5) {
          result1 = new DriverResult(driver1, 100000000);
        } else {
          result2 = new DriverResult(driver2, 100000000);
        }
      } else {
        result1 = new DriverResult(driver1, 100000000);
        result2 = new DriverResult(driver2, 100000000);
      }

    }
    getResults().addAll(Arrays.asList(result1, result2));
  }

  /**
   * traverses through the list of aiteams  and playerteam
   * and adds the results of their drivers to the results of the current race.
   */
  public void handleResults() {
    this.aiteams.getTeams().forEach(this::addDriverResults);
    addDriverResults(this.playerteam);
  }

  /**
   * Sets TeamID for every driver.
   */
  public void setTeamIDs() {

    for (AiTeam team : this.getAiteams()) {
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
   * @return true if the buy was successful
   */
  public boolean driverBuy(Driver driver) {
    int budget = this.getPlayerteam().getBudget();

    if (budget > driver.getValue()) {
      if (driver.getTeamId() == 0) {
        if (!(driver.getTeamId() == 0)) {
          this.aiteams.getAiTeamById(driver.getTeamId()).getDriverList().remove(driver);
        }

      }
      driver.setTeamId(1);
      this.playerteam.addDriver(driver);
      this.playerteam.lowerBudget(driver.getValue());

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
   * Random Aiteams buys random driver but only if this driver is better than
   * one if it's own drivers.
   */
  public void buyRandomDriver() {
    Random rand = new Random();
    int random = rand.nextInt(Constants.MAX_BUYS);
    for (int i = 0; i < random; i++) {
      AiTeam randomTeam = this.getAiteams().get(new Random().nextInt(this.getAiteams().size()));
      Driver randomDriver = this.getDrivers().get(new Random().nextInt(this.getDrivers().size()));

      boolean bool = false;
      for (Driver driver :
          randomTeam.getDriverList()) {
        if (driver.getValue() < randomDriver.getValue() * 0.80) {
          bool = true;
        }
      }
      if (bool) {
        aiBuy(randomTeam, randomDriver);
      }
    }
  }

  /**
   * Aiteam driver buy.
   *
   * @param team   the aiteam that buys a driver
   * @param driver the driver that gets bought
   */
  public void aiBuy(AiTeam team, Driver driver) {
    String msg;

    if (driver.getTeamId() == 1) {
      this.playerteam.getDriverList().remove(driver);
      this.playerteam.addBudget(driver.getValue());


      msg = String.format("%s has been purchased by %s, your balance has increased by $ %s",
        driver.getName(), team.getName(), driver.getValue());
    } else {
      if (driver.getTeamId() != 0) {
        this.aiteams.getAiTeamById(driver.getTeamId()).getDriverList().remove(driver);
      }
      msg = String.format("%s has been purchased by %s", driver.getName(), team.getName());
    }
    driver.setTeamId(team.getId());
    team.addDriver(driver);

    GameEvent event = new GameEvent(msg, GameEvent.Type.TRANSFER);
    this.events.addEvent(event);
  }

  /**
   * Checks whether the player is able to buy then engine, and if so, buys it.
   * Returns true if successfull, else false.
   *
   * @param engine Pass the engine that should be bought by the player
   */
  public boolean engineBuy(Engine engine) {
    int budget = this.getPlayerteam().getBudget();
    int sellprice = this.getPlayerteam().getCar().getEngine().sellPrice();
    int effectiveprice = (int) (engine.getPrice() - sellprice);

    if (budget >= effectiveprice) {
      this.playerteam.getCar().setEngine(engine);
      this.playerteam.lowerBudget(effectiveprice);

      String msg = "You have bought a " + engine.getBrand() + " engine!";
      GameEvent event = new GameEvent(msg, GameEvent.Type.TRANSFER);
      this.events.addEvent(event);
      return true;
    }
    return false;
  }

  /**
   * Sorts results of the race by time and prints them out (for testing purposes).
   */
  public void sortResults() {
    getResults().sort(byTime);
    getResults().forEach(System.out::println);
  }

  /**
   * Method used to return the position in the race.
   *
   * @return gameevent with as message the standings
   */
  public GameEvent gameEventPositions() {

    ArrayList<Integer> positions = new ArrayList<Integer>();

    for (int i = 0; i < getResults().size(); i++) {

      if (getTeamDriver(getResults().get(i).getDriver().getTeamId()) instanceof PlayerTeam) {
        positions.add(i + 1);
      }

    }
    GameEvent event = new GameEvent(String.format("You finished %d and %d in the %s (Race #%d)",
        positions.get(0), positions.get(1), getSeason().getCurrentRaceInstance().getName(),
        getCurrentRace() + 1), GameEvent.Type.RACE);
    events.addEvent(event);
    return event;
  }

  /**
   * Method used to return a message saying your driver crashed.
   *
   * @return gameevent
   */
  public GameEvent gameEventCrashed() {

    for (int i = 0; i < getResults().size(); i++) {
      if (getTeamDriver(getResults().get(i).getDriver().getTeamId()) instanceof PlayerTeam
          && getResults().get(i).getTime() == 100000000) {
        GameEvent event = new GameEvent("Oh no... your driver "
            + getResults().get(i).getDriver().getName() + " has crashed!", GameEvent.Type.RACE);
        events.addEvent(event);
        return event;
      }
    }
    return null;
  }

  /**
   * Adds points and money to the teams according to the race results.
   * 2 million dollars per point
   */
  @SuppressWarnings("CheckStyle")
  public void addRaceWinnings() {
    for (int i = 0; i < 10; i++) {

      if (getTeamDriver(getResults().get(i).getDriver().getTeamId()) instanceof PlayerTeam) {

        PlayerTeam playerTeam = (PlayerTeam) getTeamDriver(getResults().get(i)
            .getDriver().getTeamId());

        switch (i) {
          case 0:
            setpoints(i, 25);
            playerTeam.setBudget(playerTeam.getBudget() + 25 * 2000000);

            break;
          case 1:
            setpoints(i, 18);
            playerTeam.setBudget(playerTeam.getBudget() + 18 * 2000000);

            break;
          case 2:
            setpoints(i, 15);
            playerTeam.setBudget(playerTeam.getBudget() + 15 * 2000000);

            break;
          case 3:
            setpoints(i, 12);
            playerTeam.setBudget(playerTeam.getBudget() + 12 * 2000000);

            break;
          case 4:
            setpoints(i, 10);
            playerTeam.setBudget(playerTeam.getBudget() + 10 * 2000000);

            break;
          case 5:
            setpoints(i, 8);
            playerTeam.setBudget(playerTeam.getBudget() + 8 * 2000000);

            break;
          case 6:
            setpoints(i, 6);
            playerTeam.setBudget(playerTeam.getBudget() + 6 * 2000000);

            break;
          case 7:
            setpoints(i, 4);
            playerTeam.setBudget(playerTeam.getBudget() + 4 * 2000000);

            break;
          case 8:
            setpoints(i, 2);
            playerTeam.setBudget(playerTeam.getBudget() + 2 * 2000000);

            break;
          case 9:
            setpoints(i, 1);
            playerTeam.setBudget(playerTeam.getBudget() + 2000000);

            break;
          default:
            break;
        }
      } else {
        switch (i) {
          case 0:
            setpoints(i, 25);
            break;
          case 1:
            setpoints(i, 18);
            break;
          case 2:
            setpoints(i, 15);
            break;
          case 3:
            setpoints(i, 12);
            break;
          case 4:
            setpoints(i, 10);
            break;
          case 5:
            setpoints(i, 8);
            break;
          case 6:
            setpoints(i, 6);
            break;
          case 7:
            setpoints(i, 4);
            break;
          case 8:
            setpoints(i, 2);
            break;
          case 9:
            setpoints(i, 1);
            break;
          default:
            break;
        }
      }
    }
  }

  /**
   * Sets points according to the entered number of points.
   *
   * @param driver The driver you want points added to
   * @param points Number of points
   */

  public void setpoints(int driver, int points) {

    getTeamDriver(getResults().get(driver).getDriver().getTeamId())
        .setPoints(getTeamDriver(getResults().get(driver).getDriver().getTeamId()).getPoints()
        + points);

  }

  /**
   * Gives bonuses when the player wins the season.
   */
  public void championAward() {

    Team winner = this.getSeason().getStandings().get(0);

    if (winner instanceof PlayerTeam) {

      ((PlayerTeam) winner).setBudget(((PlayerTeam) winner).getBudget() + 200000000);

    }

    Driver driver1 = winner.getDriverList().get(0);
    Driver driver2 = winner.getDriverList().get(1);

    if (driver1.getValue() > driver2.getValue() || driver1.getValue() == driver2.getValue()) {

      double newvalue = driver1.getValue() + driver1.getValue() * 0.10;
      driver1.setValue((int) newvalue);

    } else {
      double newvalue = driver2.getValue() + driver2.getValue() * 0.10;
      driver2.setValue((int) newvalue);
    }
  }

  /**
   * returns a team according to an id.
   *
   * @param id id of the team
   * @return instance of team
   */

  public Team getTeamDriver(int id) {
    for (AiTeam team : this.getAiteams()) {
      if (team.getId() == id) {
        return team;
      }
    }
    return playerteam;
  }

  /**
   * Adds latest standings to Season class.
   *
   * @return arraylist of teams
   */
  public GameEvent updateStandings() {

    ArrayList<Team> teams = new ArrayList<Team>();

    teams.addAll(this.getAiteams());
    teams.add(this.getPlayerteam());

    ArrayList<Team> standings = teams.stream().sorted(byPoints)
        .collect(Collectors.toCollection(ArrayList::new));

    this.getSeason().setStandings(standings);

    if (standings.get(0) instanceof PlayerTeam) {

      GameEvent event = new GameEvent("Congratulations! You are first in the overall standings!",
          GameEvent.Type.RACE);
      events.addEvent(event);
      return event;

    }

    return null;

  }

  /**
   * Returns the costs of the next race.
   *
   * @return double with costs
   */
  public double getRaceCost() {

    double salary1 = getFirstDriver().getValue() / 100;
    double salary2 = getSecondDriver().getValue() / 100;
    double tires = this.getPlayerteam().getCar().getTyres().getHardness() * 250000;
    double softwaretester = 0;

    if (this.playerteam.hasSoftwareTester()) {
      softwaretester = 1000;
    }


    return salary1 + salary2 + tires + softwaretester;


  }

  /**
   * Removes the cost of the race from the players budget.
   */
  public void payRace() {
    int costs = (int) getRaceCost();
    this.playerteam.lowerBudget(costs);
    DecimalFormat formatter = new DecimalFormat("#,###");
    events.addEvent(new GameEvent("You paid " + "$" + formatter.format(costs)
        + " for Race #" + (getCurrentRace() + 1), GameEvent.Type.RACE));
  }

  /**upgrades the Aerodynamisicst.
   *
   * @return a bolean representing wether it was succesfull.
   */
  public boolean upgradeAeorodynamicist() {
    Aerodynamicist aero = playerteam.getAerodynamicist();
    int budget = playerteam.getBudget();
    if (Constants.AERODYNAMICIST_UPGRADE_PRICE < budget && aero.getExpertise() < 99) {
      playerteam.lowerBudget(Constants.AERODYNAMICIST_UPGRADE_PRICE);
      GameEvent event = playerteam.getAerodynamicist().upgrade(0);
      events.addEvent(event);
      return true;
    }
    return false;
  }

  /**upgrades the Strategist.
   *
   * @return a bolean representing wether it was succesfull.
   */
  public boolean upgradeStrategist() {
    Strategist strategist = playerteam.getStrategist();
    int budget = playerteam.getBudget();
    if (Constants.STRATEGIST_UPGRADE_PRICE < budget && strategist.getRating() < 99) {
      playerteam.lowerBudget(Constants.STRATEGIST_UPGRADE_PRICE);
      GameEvent event = strategist.upgrade(0);
      events.addEvent(event);
      return true;
    }
    return false;
  }

  /**upgrades the Mechanic.
   *
   * @return a bolean representing wether it was succesfull.
   */
  public boolean upgradeMechanic() {
    Mechanic mech = playerteam.getMechanic();
    int budget = playerteam.getBudget();
    if (playerteam.getMechanic().getUpgradePrice() < budget && mech.getPitstopTime() > 2) {
      playerteam.lowerBudget(playerteam.getMechanic().getUpgradePrice());
      GameEvent event = mech.upgrade(0);
      events.addEvent(event);
      return true;
    }
    return false;
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

  public Driver getFirstDriver() {
    return playerteam.getDriverList().get(0);
  }

  public Driver getSecondDriver() {
    return playerteam.getDriverList().get(1);
  }

  public ArrayList<Engine> getEngines() {
    return engines.getEngines();
  }

  public void setEngines(EngineList engines) {
    this.engines = engines;
  }
}
