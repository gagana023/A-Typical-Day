package app;

import java.util.List;
import java.util.Map;

/**
 * Stores and provides the player's dialogue choices for each room, task, and task step.
 *
 * <p>This class connects each room/step type to a list of dialogue options that the player can
 * choose from during an interaction.
 */
public class UserDialogueEngine {
  /**
   * Represents the room or task-step situation that determines which player dialogue options
   * should be used. Each multi-step task has one enum value per step (STEP2, STEP3, ...); the base
   * value with no suffix is always the first step.
   */
  public enum Room {
    OFFICE,
    OFFICE_STEP2,
    OFFICE_STEP3,
    OFFICE_FORM,
    OFFICE_FORM_STEP2,
    OFFICE_FORM_STEP3,
    OFFICE_SCHEDULE,
    OFFICE_SCHEDULE_STEP2,
    OFFICE_SCHEDULE_STEP3,
    LIBRARY,
    LIBRARY_STEP2,
    LIBRARY_STEP3,
    CAFETERIA,
    CAFETERIA_STEP2,
    CAFETERIA_STEP3,
    CLASSROOM_HOMEWORK,
    CLASSROOM_HOMEWORK_STEP2,
    CLASSROOM_HOMEWORK_STEP3,
    CLASSROOM_PROBLEM,
    CLASSROOM_PROBLEM_STEP2,
    CLASSROOM_PROBLEM_STEP3,
    CAFETERIA_JOIN_TABLE,
    CAFETERIA_JOIN_TABLE_STEP2,
    CAFETERIA_JOIN_TABLE_STEP3,
    LIBRARY_RETURN,
    LIBRARY_RETURN_STEP2,
    LIBRARY_RETURN_STEP3,
    LIBRARY_BOOK,
    LIBRARY_BOOK_STEP2,
    LIBRARY_BOOK_STEP3
  }

  /**
   * Stores the player dialogue options for each room or task-step situation.
   *
   * <p>Each entry maps to a list of possible choices shown to the player.
   */
  private static final Map<Room, List<String>> dialogues =
      Map.ofEntries(
          Map.entry(
              Room.OFFICE,
              List.of(
                  "Good morning. Could I please meet with the counselor when she is available?",
                  "Hi, can I talk to the counselor?",
                  "I need to see the counselor right now.")),
          Map.entry(
              Room.OFFICE_STEP2,
              List.of(
                  "I've been having a hard week and could really use a little more time.",
                  "I need a few more days to finish it.",
                  "I just don't feel like doing it right now.")),
          Map.entry(
              Room.OFFICE_STEP3,
              List.of(
                  "Thank you so much, I really appreciate it.",
                  "Thanks.",
                  "Whatever, I'm leaving.")),
          Map.entry(
              Room.OFFICE_FORM,
              List.of(
                  "Hi, could I please take one of these counselor request forms?",
                  "Is this the form I need to fill out?",
                  "I'm just going to take this form.")),
          Map.entry(
              Room.OFFICE_FORM_STEP2,
              List.of(
                  "Could you let me know if I'm filling this out correctly?",
                  "I'll fill it out now.",
                  "This is confusing, why do I even need this?")),
          Map.entry(
              Room.OFFICE_FORM_STEP3,
              List.of(
                  "Here's the completed form, thank you for your help.",
                  "Here's the form.",
                  "Here, take it.")),
          Map.entry(
              Room.OFFICE_SCHEDULE,
              List.of(
                  "Hi, I'm confused about my schedule. Could you help me check it?",
                  "I think something on my schedule might be wrong.",
                  "My schedule makes no sense. Can someone fix it?")),
          Map.entry(
              Room.OFFICE_SCHEDULE_STEP2,
              List.of(
                  "I think I'm missing a class on Tuesdays, could you check?",
                  "This part looks off to me.",
                  "This whole thing is wrong, fix it.")),
          Map.entry(
              Room.OFFICE_SCHEDULE_STEP3,
              List.of(
                  "Thank you for fixing that so quickly.",
                  "Thanks for checking.",
                  "Fine, whatever.")),
          Map.entry(
              Room.LIBRARY,
              List.of(
                  "Hey guys, I'm so nervous for this test tomorrow! Mind if I study with y'all?",
                  "Would you mind if I studied with you guys?",
                  "I need a group to join, I guess. So here I am.")),
          Map.entry(
              Room.LIBRARY_STEP2,
              List.of(
                  "Thanks for making room, I really appreciate it!",
                  "I'll just sit here.",
                  "Scoot over, I need more space.")),
          Map.entry(
              Room.LIBRARY_STEP3,
              List.of(
                  "Would anyone mind explaining this section to me?",
                  "Can someone help me with this part?",
                  "This is so easy, why are you all stuck on it?")),
          Map.entry(
              Room.CAFETERIA,
              List.of(
                  "Hey! Today, I'll have...actually, is there anything you recommend that's"
                      + " vegetarian?",
                  "I need something vegetarian.",
                  "I don't like your food. I'd like something prepackaged.")),
          Map.entry(
              Room.CAFETERIA_STEP2,
              List.of(
                  "The veggie pizza sounds perfect, I'll take that, thank you!",
                  "I'll take the veggie pizza.",
                  "None of this looks good, just give me something.")),
          Map.entry(
              Room.CAFETERIA_STEP3,
              List.of("Thank you so much, have a great day!", "Thanks.", "Finally.")),
          Map.entry(
              Room.CLASSROOM_HOMEWORK,
              List.of(
                  "I had a lot going on at home and needed extra time.",
                  "I forgot to finish it, but I brought it now.",
                  "I just told you I wasn't able to finish it on time.")),
          Map.entry(
              Room.CLASSROOM_HOMEWORK_STEP2,
              List.of(
                  "I had a lot going on at home and I'm sorry it's late.",
                  "I forgot, but I finished it as soon as I remembered.",
                  "I just didn't feel like doing it on time.")),
          Map.entry(
              Room.CLASSROOM_HOMEWORK_STEP3,
              List.of(
                  "Is there anything I can do to make up for the lateness?",
                  "Will there be a penalty?",
                  "There better not be a penalty.")),
          Map.entry(
              Room.CLASSROOM_PROBLEM,
              List.of(
                  "No, I do not know how to solve it.",
                  "No, I am not ready to answer.",
                  "No, the question is too hard.")),
          Map.entry(
              Room.CLASSROOM_PROBLEM_STEP2,
              List.of(
                  "I'm not sure, but I'd like to try if that's okay.",
                  "I don't think I can solve it.",
                  "No, and I don't want to try.")),
          Map.entry(
              Room.CLASSROOM_PROBLEM_STEP3,
              List.of(
                  "Okay, I think the first step is this, right?",
                  "I'll try the first step.",
                  "I still don't get it.")),
          Map.entry(
              Room.CAFETERIA_JOIN_TABLE,
              List.of(
                  "Hey, is it okay if I sit with you guys?",
                  "I might sit here for a bit if that's okay.",
                  "You guys look less annoying than everyone else, so I'm sitting here.")),
          Map.entry(
              Room.CAFETERIA_JOIN_TABLE_STEP2,
              List.of(
                  "Thanks for letting me sit here, I appreciate it!",
                  "I'll just sit here then.",
                  "Finally, move your stuff over.")),
          Map.entry(
              Room.CAFETERIA_JOIN_TABLE_STEP3,
              List.of(
                  "Hi, I'm not sure we've met, I'm excited to get to know you all!",
                  "Hey, I'm new here.",
                  "You don't need to know my name.")),
          Map.entry(
              Room.LIBRARY_RETURN,
              List.of(
                  "Hi, I'd like to return this book, please.",
                  "Can I put this book in the return bin?",
                  "I'm asumming I can drop this book here?")),
          Map.entry(
              Room.LIBRARY_RETURN_STEP2,
              List.of(
                  "I'll place it carefully in the return cart, thank you!",
                  "I'll put it in the cart.",
                  "I'll just leave it here.")),
          Map.entry(
              Room.LIBRARY_RETURN_STEP3,
              List.of(
                  "Just wanted to confirm you got it, thank you!",
                  "Did you get it okay?",
                  "You better have gotten it.")),
          Map.entry(
              Room.LIBRARY_BOOK,
              List.of(
                  "Hi, could I please check out this book?",
                  "Can I borrow this book?",
                  "So, can I take the book with me?")),
          Map.entry(
              Room.LIBRARY_BOOK_STEP2,
              List.of(
                  "Here's my library card, thank you for helping me!",
                  "Here's my card.",
                  "Here, just scan it already.")),
          Map.entry(
              Room.LIBRARY_BOOK_STEP3,
              List.of(
                  "Thank you, when is this due back?",
                  "When's it due?",
                  "I don't even need to know, whatever.")));

  /**
   * Returns the player's dialogue options for the given room or task-step situation.
   *
   * @param room the room or task-step situation used to choose the player dialogue options
   * @return the list of player dialogue options for the given room/step
   */
  public static List<String> getOptions(Room room) {
    return dialogues.getOrDefault(room, List.of("..."));
  }
}