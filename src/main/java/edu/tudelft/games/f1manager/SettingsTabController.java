package edu.tudelft.games.f1manager;

public class SettingsTabController {

  private ClientController clientController;

  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }
}
