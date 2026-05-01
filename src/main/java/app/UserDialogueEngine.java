package app;

import java.util.List;
import java.util.Map;

public class UserDialogueEngine {

  public enum Room {
    OFFICE,
    LIBRARY,
    CAFETERIA,
    CLASSROOM_HOMEWORK,
    CLASSROOM_PROBLEM
  }

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
              "No, I would rather not answer in front of everyone."));

  public static List<String> getOptions(Room room) {
    return dialogues.getOrDefault(room, List.of("..."));
  }
}
