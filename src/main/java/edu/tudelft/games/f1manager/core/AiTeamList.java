package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

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
  private ArrayList<AiTeam> teams;


  /**
   * Creates an object that represents a list of ai teams.
   * Has a fixed size of 10
   */
  public AiTeamList() {
    this.teams = new ArrayList<>();
  }

  /**
   * Adds an aiteam to the list of aiteams.
   *
   * @param team the aiteam that gets added
   */
  public void add(AiTeam team) {
    this.getTeams().add(team);
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

    return gson.fromJson(reader, AiTeamList.class);
  }


  /**
   * Write the aiteamlist to aiteamlist.json.
   *
   * @throws IOException when the file doesn't exist
   */
  public void write(String filename) throws IOException {

    String json = gson.toJson(this);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();
  }

  /**
   * Returnw an AiTeam from the list of teams if exists one having an Id matching the id provided.
   *
   * @param id the id that gets compared
   * @return an AiTeam if there exists one having the id, else null
   */
  public AiTeam getAiTeamById(int id) {
    for (AiTeam team : this.getTeams()) {
      if (team.getId() == id) {
        return team;
      }
    }
    return null;
  }

  public ArrayList<AiTeam> getTeams() {
    return teams;
  }

  public void setTeams(ArrayList<AiTeam> teams) {
    this.teams = teams;
  }
}