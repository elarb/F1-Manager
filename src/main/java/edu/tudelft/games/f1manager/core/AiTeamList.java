package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * Class that represents a list of ComputerTeams
 */
public class AiTeamList {

  /**
   * A list of ComputerTeams.
   */
  private ArrayList<AiTeam> AiTeamList;
  private Gson gson = new GsonBuilder()
    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    .serializeNulls()
    .create();

  /**
   * constructor for AiTeamList
   */
  public AiTeamList() {
  }

  public ArrayList<AiTeam> getAiTeamList() {
    return AiTeamList;
  }

  public void setAiTeamList(ArrayList<AiTeam> aiTeamList) {
    this.AiTeamList = aiTeamList;
  }

  public AiTeamList read() {

    String fileName = "AiTeamList.json";

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getSystemClassLoader().getResourceAsStream("JSON/" + fileName);
    Reader reader = new InputStreamReader(is);
    ArrayList<AiTeam> aiTeamArrayList = gson.fromJson(reader, new TypeToken<ArrayList<AiTeam>>(){}.getType());
    AiTeamList aiteamlist = new AiTeamList();
    aiteamlist.setAiTeamList(aiTeamArrayList);

    return aiteamlist;

  }

  public void getJSON() {

    AiTeamList newaiteamlist = read();
    this.AiTeamList = newaiteamlist.getAiTeamList();

  }


  public void updateJSON() throws IOException {

    String fileName = "AiTeamList.json";

    String json = gson.toJson(this.AiTeamList);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + fileName);
    outputStream.write(json.getBytes());
    outputStream.close();

  }

}