package app;

import java.util.List;
import java.util.Map;

/**
 * Stores and provides NPC dialogue responses for each room or task situation.
 *
 * <p>This class matches each room type to a list of possible NPC responses. These responses are
 * used by the dialogue system after the player selects a dialogue option.
 */
public class NPCDialogue {
  /**
   * Represents the room or classroom task situation that determines which NPC dialogue responses
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
   * Stores the NPC dialogue responses for each room or classroom task situation.
   *
   * <p>Each room maps to a list of possible responses that match the player's dialogue choices.
   */
  private static final Map<Room, List<String>> npcDialogues =
      Map.of(
          Room.OFFICE,
          List.of(
              "Of course sweetie, just walk straight down and her door is the second one on the"
                  + " right.",
              "Sure, her door is the second one on the right.",
              "Wow, good morning to you too. I'll be with you in a few."),
          Room.LIBRARY,
          List.of(
              "Sure, girl! Great to meet you! What do you wanna work on today?",
              "Okay, just pull up a chair and join us.",
              "Um, we're actually full...sorry..."),
          Room.CAFETERIA,
          List.of(
              "Yep! So my personal favorite is the vegetable pizza, I'll cut you a slice!",
              "Mhm, just let me know when you're ready.",
              "That's so rude! I am not helping you right now. Can someone come help?"),
          Room.CLASSROOM_HOMEWORK,
          List.of(
              "Thank you for being honest. I will accept it late, but please talk to me earlier"
                  + " next time.",
              "I will take it this time, but you need to stay more organized.",
              "That is not a good reason. I will accept it, but there may be a late penalty."),
          Room.CLASSROOM_PROBLEM,
          List.of(
              "That is okay. We can work through the first step together.",
              "I understand. Next time, try to be more prepared before class starts.",
              "I will not force you to answer, but participation matters."),
          Room.CAFETERIA_JOIN_TABLE,
          List.of(
              "Yeah, of course! Pull up a chair.", "Sure, that's fine.", "Uh... okay, I guess."),
          Room.OFFICE_FORM,
          List.of(
              "Yes, go ahead. You can fill it out and return it here.",
              "That's the right one. Let me know if you need help.",
              "Please ask before taking things from the desk."),
          Room.OFFICE_SCHEDULE,
          List.of(
              "Of course. Let's look through it together.",
              "Sure, I can check it for you.",
              "I can help, but please speak kindly."),
          Room.LIBRARY_RETURN,
          List.of(
              "Thank you for returning it on time!",
              "Yes, the return bin is the right place.",
              "Please make sure it goes in the return cart next time."),
          Room.LIBRARY_BOOK,
          List.of(
              "Of course! I'll scan it for you now.",
              "Sure, just make sure to return it on time.",
              "Please check it out properly before leaving."));

  /**
   * Returns the NPC dialogue options for the given room or task situation.
   *
   * @param room the room or task situation used to choose the NPC dialogue
   * @return the list of NPC dialogue responses for the given room
   */
  public static List<String> getOptions(Room room) {
    return npcDialogues.getOrDefault(room, List.of("..."));
  }
}
