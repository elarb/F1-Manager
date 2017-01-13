package edu.tudelft.games.f1manager.game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameEvent {

  /**
   * The time the game-event occured.
   */
  private String currentDateTime;

  /**
   * Message associated to the game-event
   */
  private String message;

  /**
   * Type of the game-event.
   */
  private Type type;

  /**
   * True if the game event was successfull.
   */
  private boolean success;

  public GameEvent(String message, Type type) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime dateTime = LocalDateTime.now();
    this.currentDateTime = dateTime.format(formatter);
    this.message = message;
    this.type = type;
  }

  public GameEvent(String message, Type type, boolean success) {
    this(message, type);
    this.success = success;
  }

  public enum Type {
    UPGRADE, TRANSFER, RACE
  }

  public String getCurrentDateTime() {
    return currentDateTime;
  }

  public void setCurrentDateTime(String currentDateTime) {
    this.currentDateTime = currentDateTime;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
