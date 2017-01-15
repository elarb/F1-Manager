package edu.tudelft.games.f1manager.core;

/**
 * Objects that are upgradeable should implement this interface.
 */
public interface Upgradeable<E> {

  E upgrade(int stat);
  // Driver: 0 = speed, 1 = racecraft, 2 = strategyinsight

//  void upgradeBy(int num);

}
