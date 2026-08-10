package app;

import java.util.List;
import java.util.Map;

/**
 * Stores and provides NPC dialogue responses for each room, task, and task step.
 *
 * <p>This class matches each room/step type to a list of possible NPC responses. These responses
 * are used by the dialogue system after the player selects a dialogue option.
 */
public class NPCDialogue {
  /**
   * Represents the room or task-step situation that determines which NPC dialogue responses
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
   * Stores the NPC dialogue responses for each room or task-step situation.
   *
   * <p>Each entry maps to a list of possible responses that match the player's dialogue choices.
   */
  private static final Map<Room, List<String>> npcDialogues =
      Map.ofEntries(
          Map.entry(
              Room.OFFICE,
              List.of(
                  "Of course sweetie, just walk straight down and her door is the second one on"
                      + " the right.",
                  "Sure, her door is the second one on the right.",
                  "Wow, good morning to you too. I'll be with you in a few.")),
          Map.entry(
              Room.OFFICE_STEP2,
              List.of(
                  "That's completely understandable. I'll note it down for you.",
                  "Okay, I can give you a short extension.",
                  "That's not really a valid reason, but I'll allow it this once.")),
          Map.entry(
              Room.OFFICE_STEP3,
              List.of(
                  "You're welcome! Come back anytime you need help.",
                  "No problem, take care.",
                  "...Alright, have a good day.")),
          Map.entry(
              Room.OFFICE_FORM,
              List.of(
                  "Yes, go ahead. You can fill it out and return it here.",
                  "That's the right one. Let me know if you need help.",
                  "Please ask before taking things from the desk.")),
          Map.entry(
              Room.OFFICE_FORM_STEP2,
              List.of(
                  "Of course, just ask if you get stuck.",
                  "Sounds good, take your time.",
                  "It helps us keep track of requests, that's all.")),
          Map.entry(
              Room.OFFICE_FORM_STEP3,
              List.of(
                  "Perfect, thank you for filling it out carefully.",
                  "Great, I'll process this for you.",
                  "...Okay, thanks.")),
          Map.entry(
              Room.OFFICE_SCHEDULE,
              List.of(
                  "Of course. Let's look through it together.",
                  "Sure, I can check it for you.",
                  "I can help, but please speak kindly.")),
          Map.entry(
              Room.OFFICE_SCHEDULE_STEP2,
              List.of(
                  "Let me take a look... you're right, I'll fix that.",
                  "I see it, let me update that for you.",
                  "Let's see what's going on here.")),
          Map.entry(
              Room.OFFICE_SCHEDULE_STEP3,
              List.of(
                  "Anytime! Let me know if anything else looks off.",
                  "No problem at all.",
                  "...Sure.")),
          Map.entry(
              Room.LIBRARY,
              List.of(
                  "Sure, girl! Great to meet you! What do you wanna work on today?",
                  "Okay, just pull up a chair and join us.",
                  "Um, we're actually full...sorry...")),
          Map.entry(
              Room.LIBRARY_STEP2,
              List.of("Of course! We're glad to have you.", "Sure thing.", "...Okay, sure.")),
          Map.entry(
              Room.LIBRARY_STEP3,
              List.of(
                  "Sure! Let's go through it together.",
                  "Yeah, I can help with that.",
                  "...We're doing our best, actually.")),
          Map.entry(
              Room.CAFETERIA,
              List.of(
                  "Yep! So my personal favorite is the vegetable pizza, I'll cut you a slice!",
                  "Mhm, just let me know when you're ready.",
                  "That's so rude! I am not helping you right now. Can someone come help?")),
          Map.entry(
              Room.CAFETERIA_STEP2,
              List.of("Great choice! Coming right up.", "Sure thing.", "...Alright, here you go.")),
          Map.entry(
              Room.CAFETERIA_STEP3,
              List.of("You too! Enjoy your meal.", "You're welcome.", "...Next.")),
          Map.entry(
              Room.CLASSROOM_HOMEWORK,
              List.of(
                  "Thank you for being honest. I will accept it late, but please talk to me"
                      + " earlier next time.",
                  "I will take it this time, but you need to stay more organized.",
                  "That is not a good reason. I will accept it, but there may be a late"
                      + " penalty.")),
          Map.entry(
              Room.CLASSROOM_HOMEWORK_STEP2,
              List.of(
                  "Thank you for telling me. I understand things come up.",
                  "Okay, thank you for being honest.",
                  "I appreciate you turning it in, even if it's late.")),
          Map.entry(
              Room.CLASSROOM_HOMEWORK_STEP3,
              List.of(
                  "Since you were honest, I'll waive it this time.",
                  "There will be a small deduction, but that's alright.",
                  "Yes, there will be, but I appreciate you asking.")),
          Map.entry(
              Room.CLASSROOM_PROBLEM,
              List.of(
                  "That is okay. We can work through the first step together.",
                  "I understand. Next time, try to be more prepared before class starts.",
                  "I will not force you to answer, but participation matters.")),
          Map.entry(
              Room.CLASSROOM_PROBLEM_STEP2,
              List.of(
                  "That's a great attitude, let's work through it.",
                  "That's okay, we'll go through it together.",
                  "That's alright, we can still work on it.")),
          Map.entry(
              Room.CLASSROOM_PROBLEM_STEP3,
              List.of(
                  "Exactly! Great job.", "Good try, let's continue.", "That's alright, let's take it slow.")),
          Map.entry(
              Room.CAFETERIA_JOIN_TABLE,
              List.of(
                  "Yeah, of course! Pull up a chair.", "Sure, that's fine.", "Uh... okay, I guess.")),
          Map.entry(
              Room.CAFETERIA_JOIN_TABLE_STEP2,
              List.of("Of course! Glad to have you.", "Sure.", "...Okay.")),
          Map.entry(
              Room.CAFETERIA_JOIN_TABLE_STEP3,
              List.of("Nice to meet you! Welcome.", "Nice to meet you too.", "...Okay then.")),
          Map.entry(
              Room.LIBRARY_RETURN,
              List.of(
                  "Thank you for returning it on time!",
                  "Yes, the return bin is the right place.",
                  "Please make sure it goes in the return cart next time.")),
          Map.entry(
              Room.LIBRARY_RETURN_STEP2,
              List.of(
                  "Perfect, thank you for being so careful.",
                  "Thanks, that works.",
                  "Please make sure it's actually in the cart, not just nearby.")),
          Map.entry(
              Room.LIBRARY_RETURN_STEP3,
              List.of("Yes, all set! Thanks for checking.", "Yep, all good.", "...Yes, it's here.")),
          Map.entry(
              Room.LIBRARY_BOOK,
              List.of(
                  "Of course! I'll scan it for you now.",
                  "Sure, just make sure to return it on time.",
                  "Please check it out properly before leaving.")),
          Map.entry(
              Room.LIBRARY_BOOK_STEP2,
              List.of(
                  "Thank you! Let me scan this for you.", "Got it, one moment.", "...Alright.")),
          Map.entry(
              Room.LIBRARY_BOOK_STEP3,
              List.of(
                  "It's due back in two weeks, enjoy the read!",
                  "Two weeks from today.",
                  "...Two weeks. Please don't lose it.")));

  /**
   * Returns the NPC dialogue options for the given room or task-step situation.
   *
   * @param room the room or task-step situation used to choose the NPC dialogue
   * @return the list of NPC dialogue responses for the given room/step
   */
  public static List<String> getOptions(Room room) {
    return npcDialogues.getOrDefault(room, List.of("..."));
  }
}