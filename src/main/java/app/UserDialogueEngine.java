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
    OFFICE_FORM,
    OFFICE_SCHEDULE,
    LIBRARY,
    CAFETERIA,
    CLASSROOM_HOMEWORK,
    CLASSROOM_PROBLEM,
    CAFETERIA_JOIN_TABLE,
    LIBRARY_RETURN,
    LIBRARY_BOOK
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
              "Good morning. Could I please meet with the counselor when she is available?",
              "Hi, can I talk to the counselor?",
              "I need to see the counselor right now."),
          Room.LIBRARY,
          List.of(
              "Hey guys, I'm so nervous for this test tomorrow! Mind if I study with y'all?",
              "Would you mind if I studied with you guys?",
              "I need a group to join, I guess. So here I am."),
          Room.CAFETERIA,
          List.of(
              "Hey! Today, I'll have...actually, is there anything you recommend that's"
                  + " vegetarian?",
              "I need something vegetarian.",
              "I don't like your food. I'd like something prepackaged."),
          Room.CLASSROOM_HOMEWORK,
          List.of(
              "I had a lot going on at home and needed extra time.",
              "I forgot to finish it, but I brought it now.",
              "I just told you I wasn't able to finish it on time."),
          Room.CLASSROOM_PROBLEM,
          List.of(
              "No, I do not know how to solve it.",
              "No, I am not ready to answer.",
              "No, the question is too hard."),
          Room.CAFETERIA_JOIN_TABLE,
          List.of(
              "Hey, is it okay if I sit with you guys?",
              "I might sit here for a bit if that's okay.",
              "You guys look less annoying than everyone else, so I'm sitting here."),
          Room.OFFICE_FORM,
          List.of(
              "Hi, could I please take one of these counselor request forms?",
              "Is this the form I need to fill out?",
              "I'm just going to take this form."),
          Room.OFFICE_SCHEDULE,
          List.of(
              "Hi, I'm confused about my schedule. Could you help me check it?",
              "I think something on my schedule might be wrong.",
              "My schedule makes no sense. Can someone fix it?"),
          Room.LIBRARY_RETURN,
          List.of(
              "Hi, I'd like to return this book, please.",
              "Can I put this book in the return bin?",
              "I'm asumming I can drop this book here?"),
          Room.LIBRARY_BOOK,
          List.of(
              "Hi, could I please check out this book?",
              "Can I borrow this book?",
              "So, can I take the book with me?"));

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
