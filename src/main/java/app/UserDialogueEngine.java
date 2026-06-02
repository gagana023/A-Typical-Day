package app;

import java.util.List;
import java.util.Map;

/**
 * Stores and provides the player's dialogue choices for each room or task situation.
 *
 * <p>This class connects each room type to a list of dialogue options that the player can choose
 * from during an interaction.
 */
public class UserDialogueEngine {
  /**
   * Represents the room or classroom task situation that determines which player dialogue options
   * should be used.
   */
  public enum Room {
    OFFICE,
    LIBRARY,
    CAFETERIA,
    CLASSROOM_HOMEWORK,
    CLASSROOM_PROBLEM,
    CAFETERIA_JOIN_TABLE
  }

  /**
   * Stores the player dialogue options for each room or classroom task situation.
   *
   * <p>Each room maps to a list of possible choices shown to the player.
   */
  private static final Map<Room, List<String>> dialogues =
      Map.of(
          Room.OFFICE,
          List.of(
              "Hey, good morning! I'd like to talk to my counselor please.",
              "Can I please talk to my counselor?",
              "I need to talk to my counselor now."),
          Room.LIBRARY,
          List.of(
              "Hey guys, I'm so nervous for this test tomorrow! Mind if I study with y'all?",
              "Would you mind if I studied with you guys?",
              "I don't know you but, can I join your study group?"),
          Room.CAFETERIA,
          List.of(
              "Hey! Today, I'll have...actually, is there anything you recommend that's"
                  + " vegetarian?",
              "Sorry, can I get a little more time to look at the options?",
              "I don't like your food. I'd like something prepackaged."),
          Room.CLASSROOM_HOMEWORK,
          List.of(
              "I had a lot going on at home and needed extra time.",
              "I forgot to finish it, but I brought it now.",
              "I just did not feel like doing it on time."),
          Room.CLASSROOM_PROBLEM,
          List.of(
              "No, I do not know how to solve it.",
              "No, I am not ready to answer yet.",
              "No, I would rather not answer in front of everyone."),
          Room.CAFETERIA_JOIN_TABLE,
          List.of(
              "Hey, is it okay if I sit with you guys?",
              "I might sit here for a bit if that's okay.",
              "You guys look less annoying than everyone else, so I'm sitting here."));

  /**
   * Returns the player's dialogue options for the given room or task situation.
   *
   * @param room the room or task situation used to choose the player dialogue options
   * @return the list of player dialogue options for the given room
   */
  public static List<String> getOptions(Room room) {
    return dialogues.getOrDefault(room, List.of("..."));
  }
}
