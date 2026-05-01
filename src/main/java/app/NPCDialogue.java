package app;

import java.util.List;
import java.util.Map;

public class NPCDialogue {

    public enum Room {
        OFFICE,
        LIBRARY,
        CAFETERIA,
        CLASSROOM_HOMEWORK,
        CLASSROOM_PROBLEM
    }

    private static final Map<Room, List<String>> npcDialogues = Map.of(
        Room.OFFICE, List.of(
            "Of course sweetie, just walk straight down and her door is the second one on the right.",
            "Sure, her door is the second one on the right.",
            "Wow, good morning to you too. I'll be with you in a few."
        ),

        Room.LIBRARY, List.of(
            "Sure, girl! Great to meet you! What do you wanna work on today?",
            "Okay, just pull up a chair and join us.",
            "Um, we're actually full...sorry..."
        ),

        Room.CAFETERIA, List.of(
            "Yep! So my personal favorite is the vegetable pizza, I'll cut you a slice!",
            "Mhm, just let me know when you're ready.",
            "That's so rude! I am not helping you right now. Can someone come help?"
        ),

        Room.CLASSROOM_HOMEWORK, List.of(
    "Thank you for being honest. I will accept it late, but please talk to me earlier next time.",
    "I will take it this time, but you need to stay more organized.",
    "That is not a good reason. I will accept it, but there may be a late penalty."
),

Room.CLASSROOM_PROBLEM, List.of(
    "That is okay. We can work through the first step together.",
    "I understand. Next time, try to be more prepared before class starts.",
    "I will not force you to answer, but participation matters."
)
    );

    public static List<String> getOptions(Room room) {
        return npcDialogues.getOrDefault(room, List.of("..."));
    }
}