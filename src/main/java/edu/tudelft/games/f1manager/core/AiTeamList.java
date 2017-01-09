package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that represents a list of ComputerTeams.
 */
public class AiTeamList {

  /**
   * A list of ComputerTeams.
   */
  private ArrayList<AiTeam> aiTeamList;


  private Gson gson = new GsonBuilder()
    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    .serializeNulls().create();

  /**
   * constructor for aiTeamList.
   */
  public AiTeamList() {
  }

  public void balanceDrivers() {
	  for (int i = 0; i < this.aiTeamList.size(); i++) {
		  if (aiTeamList.get(i).getDriverList().size() < 2) {
			  if (aiTeamList.get(i).getDriverList().size() < 1) {
				  this.buyLeftoverDriver(aiTeamList.get(i));
			  }
			  this.buyLeftoverDriver(aiTeamList.get(i));
		  }
	  }
  }
  
  public void buyLeftoverDriver(AiTeam aiTeam){
	  Random rand = new Random();
	  boolean bought = true;
	  while (bought == false) {
		  Driver rDriver = DriverList.getDriverList().get(rand.nextInt(DriverList.getDriverList().size()));
		  if (rDriver.getTeam() == null) {
			  aiTeam.buyDriver(rDriver);
			  bought = true;
		  }
	  }
  }
  
  public void buyRandomDriver() {
	  Random rand = new Random();
	  int r = rand.nextInt(Constants.MAX_BUYS);
	  for (int i = 0; i < r; i++) {
		  AiTeam buyingTeam = this.getAiTeamList().get(rand.nextInt(this.getAiTeamList().size()));
		  Driver buyingDriver = DriverList.getDriverList().get(rand.nextInt(DriverList.getDriverList().size()));
		  if (buyingDriver.getTeam() != buyingTeam) {
			  buyingTeam.buyDriver(buyingDriver);
		  }
	  }
  }
  
  public ArrayList<AiTeam> getAiTeamList() {
    return aiTeamList;
  }

  public void setAiTeamList(ArrayList<AiTeam> aiTeamList) {
    this.aiTeamList = aiTeamList;
  }


  /**
   * Reads an aiTeamList from "aiTeamList.json".
   *
   * @return an aiTeamList
   */
  public AiTeamList read() {

    String fileName = "aiTeamList.json";

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getSystemClassLoader().getResourceAsStream("JSON/" + fileName);
    Reader reader = new InputStreamReader(is);
    ArrayList<AiTeam> aiTeamArrayList = gson.fromJson(reader, new TypeToken<ArrayList<AiTeam>>() {
    }.getType());
    AiTeamList aiteamlist = new AiTeamList();
    aiteamlist.setAiTeamList(aiTeamArrayList);

    return aiteamlist;

  }

  /**
   * Uses read() to initialize an aiTeamList object.
   */
  public void getJson() {

    AiTeamList newaiteamlist = read();
    this.aiTeamList = newaiteamlist.getAiTeamList();

  }

  /**
   * Updates the "aiTeamList.json" file with the changed fields
   *
   * @throws IOException throws an IO Exception
   */
  public void updateJson() throws IOException {

    String fileName = "aiTeamList.json";

    String json = gson.toJson(this.aiTeamList);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + fileName);
    outputStream.write(json.getBytes());
    outputStream.close();

  }

}