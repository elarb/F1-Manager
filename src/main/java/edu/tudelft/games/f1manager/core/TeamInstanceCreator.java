package edu.tudelft.games.f1manager.core;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;


public class TeamInstanceCreator implements InstanceCreator<Team> {

  @Override
  public Team createInstance(Type type) {

    if (type instanceof AiTeam) {

      return new AiTeam(null, null, null, null, null);

    } else {

      return new PlayerTeam(null, null, null, null, null, 0, true);
    }

  }

}
