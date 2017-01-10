package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.collections.list.FixedSizeList;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a list of Computer Teams.
 */
public class AiTeamList {

  /**
   * A list of Computer Teams.
   */
  private List<AiTeam> teams;


  /**
   * Creates an object that represents a list of ai teams.
   * Has a fixed size of 10
   */
  public AiTeamList() {
    this.teams = FixedSizeList.decorate(Arrays.asList(new AiTeam[10]));
  }


  /**
   * Reads in aiteamlist.json returns a aiteamlist
   * object if the file is in the appropriate format.
   *
   * @return a aiteamlist
   */
  public static AiTeamList read(String filename) {
//    String filename = "aiteams.json";
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);
    Gson gson = new GsonBuilder().create();
    AiTeamList aiteamlist = gson.fromJson(reader, AiTeamList.class);

    return aiteamlist;

  }


  /**
   * Write the aiteamlist to aiteamlist.json.
   *
   * @param aiteamlist the aiteamlist that gets written
   * @throws IOException when the file doesn't exist
   */
  public static void write(AiTeamList aiteamlist, String filename) throws IOException {
//    String filename = "aiteams.json";
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson(aiteamlist);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();
    System.out.println("This has been written to aiteamlist.json : " + json);
  }

  public List<AiTeam> getTeams() {
    return teams;
  }

  public void setTeams(List<AiTeam> teams) {
    this.teams = teams;
  }
}