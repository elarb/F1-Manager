package edu.tudelft.games.f1manager.game;

import edu.tudelft.games.f1manager.core.*;
import edu.tudelft.games.f1manager.tools.RandomDouble;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {

  private DriverList drivers;
  private AiTeamList aiteams;
  private PlayerTeam playerteam;
  private Season season;
  private GameEvents events;

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
    if (this.season.getCurrentRace() % 4 == 0) {
      buyRandomDriver();
    }
    balanceDrivers();
    handleResults();
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
    DriverResult result1 = new DriverResult(driver1,
      this.getCurrentRaceFactor() * team.getResultsDriver1());
    DriverResult result2 = new DriverResult(driver2,
      this.getCurrentRaceFactor() * team.getResultsDriver2());
    this.getSeason().getCurrentRaceInstance().getResults().addAll(Arrays.asList(result1, result2));
  }

  public void handleResults() {
    //traverses through the list of aiteams and adds the results of
    // their drivers to the results of the current race
    for (AiTeam team :
      this.aiteams.getTeams()) {
      addDriverResults(team);
    }
    addDriverResults(this.playerteam);
  }

  /**
   * Returns the calculated factor of the current race.
   *
   * @return the calculated factor of the current race
   */
  public double getCurrentRaceFactor() {
    //TODO by Tim: Add and tweak formula
    double somethingFactor = 10;
    double elseFactor = 30;
    double fooFactor = 40;
    return somethingFactor * elseFactor * fooFactor;
  }

  public boolean driverBuy(Driver driver) {
    int budget = this.getPlayerteam().getBudget();
    double random = RandomDouble.generatePercentage();

    if (random < 20 && budget > driver.getValue()) {
      this.playerteam.addDriver(driver);
      this.playerteam.setBudget(budget - driver.getValue());
      String msg = driver.getName() + " has been purchased by you!";
      GameEvent event = new GameEvent(msg, GameEvent.Type.TRANSFER);
      this.events.addEvent(event);
      return true;
    }
    return false;
  }

  //////////////////////////////////////////////

  public void balanceDrivers() {
    for (int i = 0; i < this.getAiteams().size(); i++) {
      if (this.getAiteams().get(i).getDriverList().size() < 2) {
        buyLeftoverDriver(this.getAiteams().get(i));
      }
    }
  }

  public void buyLeftoverDriver(AiTeam aiTeam) {
    Random rand = new Random();
    ArrayList<Driver> list = this.getDrivers();
    while (true) {
      Driver driver = list.get(rand.nextInt(list.size()));
      if (driver.getTeamId() == 0) {
        driver.setTeamId(aiTeam.getId());
        aiTeam.addDriver(driver);

        //adds this event to the list of events
        String msg = driver.getName() + " has been purchased by " + aiTeam.getName();
        GameEvent event = new GameEvent(msg, GameEvent.Type.TRANSFER);
        this.events.addEvent(event);

        break;
      }
    }
  }

  public void buyRandomDriver() {
    Random rand = new Random();
    int random = rand.nextInt(Constants.MAX_BUYS);
    for (int i = 0; i < random; i++) {
      AiTeam randomTeam = this.getAiteams().get(new Random().nextInt(this.getAiteams().size()));
      Driver randomDriver = this.getDrivers().get(new Random().nextInt(this.getDrivers().size()));
      aiBuy(randomTeam, randomDriver);
    }
  }

  public void aiBuy(AiTeam team, Driver driver) {
    team.getDriverList().add(driver);
    driver.setTeamId(team.getId());

    //adds this event to the list of events
    String msg = String.format("%s has been purchased by %s", driver.getName(), team.getName());
    GameEvent event = new GameEvent(msg, GameEvent.Type.TRANSFER);
    this.events.addEvent(event);
  }

  //////////////////////////////////////////////


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
}
