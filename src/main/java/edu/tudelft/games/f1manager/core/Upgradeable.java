package edu.tudelft.games.f1manager.core;

/**
 * Objects that are upgradeable should implement this interface.
 */
public interface Upgradeable {

  void upgrade();

  void upgradeBy(int num);

}
