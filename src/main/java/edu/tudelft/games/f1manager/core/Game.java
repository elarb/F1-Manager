package edu.tudelft.games.f1manager.core;


import edu.tudelft.games.f1manager.game.Season;

import java.io.File;
import java.io.IOException;

public class Game {

  private DriverList driverList;
  private AiTeamList aiTeamList;
  private PlayerTeam playerTeam;
  private Season season;


  public void Game(){

  }

  public void setDriverList(DriverList driverList){

    this.driverList = driverList;

  }

  public DriverList getDriverList() {
    return driverList;
  }

  public void setAiTeamList(AiTeamList aiTeamList) {
    this.aiTeamList = aiTeamList;
  }

  public AiTeamList getAiTeamList() {
    return aiTeamList;
  }

  public void setPlayerTeam(PlayerTeam playerTeam) {
    this.playerTeam = playerTeam;
  }

  public PlayerTeam getPlayerTeam() {
    return playerTeam;
  }

  public void setSeason(Season season) {
    this.season = season;
  }

  public Season getSeason() {
    return season;
  }

  public static Game loadgame(String savename){

    DriverList driverList = DriverList.read(savename + "/drivers.json");
    AiTeamList aiTeamList = AiTeamList.read(savename + "/aiteams.json");
    PlayerTeam playerTeam = PlayerTeam.read(savename + "/playerteam.json");
    Season season = Season.read(savename + "/season.json");

    Game game = new Game();

    game.setDriverList(driverList);
    game.setAiTeamList(aiTeamList);
    game.setPlayerTeam(playerTeam);
    game.setSeason(season);

    System.out.println("Succesfully loaded your game!");

    return game;

  }

  public static void newgame(){

    DriverList driverList = DriverList.read("drivers.json");
    AiTeamList aiTeamList = AiTeamList.read("aiteams.json");
    PlayerTeam playerTeam = PlayerTeam.read("playerteam.json");
    Season season = Season.read("season.json");

    System.out.println("Succesfully created a new game!");

  }

  public  static void savegame(String savename, DriverList driverList, AiTeamList aiTeamList, PlayerTeam playerTeam, Season season) throws IOException {

    File dir = new File("src/main/resources/JSON/" + savename);
    dir.mkdir();

    driverList.write(savename + "/drivers.json");
    aiTeamList.write(savename + "/aiteams.json");
    playerTeam.write(savename + "/playerteam.json");
    season.write(savename + "/season.json");

    System.out.println("Succesfully saved your game: " + savename);

  }


}
