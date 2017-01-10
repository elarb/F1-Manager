package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.collections.list.FixedSizeList;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a list of Computer Teams.
 */
public class AiTeamList {

  private static Gson gson = new GsonBuilder()
      .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
      .serializeNulls().create();

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

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getSystemClassLoader().getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);
    ArrayList<AiTeam> aiTeamArrayList = gson.fromJson(reader, new TypeToken<ArrayList<AiTeam>>() {
    }.getType());
    AiTeamList aiteamlist = new AiTeamList();
    aiteamlist.setTeams(aiTeamArrayList);

    return aiteamlist;

  }


  /**
   * Write the aiteamlist to aiteamlist.json.
   *
   * @throws IOException when the file doesn't exist
   */
  public void write(String filename) throws IOException {

    String json = gson.toJson(this.teams);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();

    System.out.println("Succesfully wrote to file");
    System.out.println(json);
  }

  public List getTeams() {
    return teams;
  }

  public void setTeams(List teams) {
    this.teams = teams;
  }
}