package edu.tudelft.games.f1manager.core;

/**
 * Objects that are upgradeable should implement this interface.
 */
public interface Upgradeable<E> {
  E upgrade(int stat);

  //  void upgradeBy(int num);

}
