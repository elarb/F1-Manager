package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * Class that represents a list of ComputerTeams.
 */
public class AiTeamList {

  /**
   * A list of ComputerTeams.
   */
  private ArrayList<AiTeam> aiTeamList;


  private static Gson gson = new GsonBuilder()
    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    .serializeNulls().create();

  /**
   * constructor for aiTeamList.
   */
  public AiTeamList() {
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
  public static AiTeamList read(String filename) {

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getSystemClassLoader().getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);
    ArrayList<AiTeam> aiTeamArrayList = gson.fromJson(reader, new TypeToken<ArrayList<AiTeam>>() {
    }.getType());
    AiTeamList aiteamlist = new AiTeamList();
    aiteamlist.setAiTeamList(aiTeamArrayList);

    return aiteamlist;

  }

  /**
   * Updates the "aiTeamList.json" file with the changed fields
   *
   * @throws IOException throws an IO Exception
   */
  public void write(String filename) throws IOException {

    String json = gson.toJson(this.aiTeamList);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();

    System.out.println("Succesfully wrote to file");
    System.out.println(json);
  }

}