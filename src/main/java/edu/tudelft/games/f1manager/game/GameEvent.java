package edu.tudelft.games.f1manager.game;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameEvent {

  /**
   * The time the game-event occured.
   */
  private String currentDateTime;

  /**
   * Message associated to the game-event.
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

  /**
   * Constructor for a game-event.
   *
   * @param message the message of the game-event
   * @param type    the type of the game-event
   */
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

  public enum Type {
    @SerializedName("0")
    UPGRADE(0),

    @SerializedName("1")
    TRANSFER(1),

    @SerializedName("2")
    RACE(2);

    private final int value;

    private Type(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

  }
}
