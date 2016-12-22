package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

/**
 * Class that represents a list of ComputerTeams
 */
public class AiTeamList {

  /**
   * A list of ComputerTeams.
   */
  private ArrayList<AiTeam> AiTeamList;
  private Gson gson = new Gson();

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
    InputStream is = classloader.getResourceAsStream(fileName);
    Reader reader = new InputStreamReader(is);
    AiTeamList aiteamlist = gson.fromJson(reader, AiTeamList.class);

    return aiteamlist;

  }

  public void getJSON() {

    AiTeamList newaiteamlist = read();
    this.AiTeamList = newaiteamlist.getAiTeamList();

  }


  public void updateJSON() throws IOException {

    String fileName = "AiTeamList.json";

    String json = gson.toJson(this.AiTeamList);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON" + fileName);
    outputStream.write(json.getBytes());
    outputStream.close();

  }

}