package app;

import java.util.List;
import java.util.Map;

public class NPCDialogue {

    public enum Room {
        OFFICE,
        LIBRARY,
        CAFETERIA,
        CLASSROOM
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
        Room.CLASSROOM, List.of(
            "Sure thing! I know you have a lot going on. I'll see you and the assignment Monday!",
            "Hmm, ok I'll give it to you. Just make sure this doesn't happen again and we'll be good.",
            "No? I guess you're going to have to do it reluctantly then; that's not my problem."
        )
    );

    public static List<String> getOptions(Room room) {
        return npcDialogues.getOrDefault(room, List.of("..."));
    }
}