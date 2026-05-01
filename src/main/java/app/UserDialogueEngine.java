package app;

import java.util.List;
import java.util.Map;

public class UserDialogueEngine {

    public enum Room {
        OFFICE,
        LIBRARY,
        CAFETERIA,
        CLASSROOM
    }

    private static final Map<Room, List<String>> dialogues = Map.of(
        Room.OFFICE, List.of(
            "Hey, good morning! I'd like to talk to my counselor please.",
            "Can I please talk to my counselor?",
            "I need to talk to my counselor now."
        ),
        Room.LIBRARY, List.of(
            "Hey guys, I'm so nervous for this test tomorrow! Mind if I study with y'all?",
            "Would you mind if I studied with you guys?",
            "I don't know you but, can I join your study group?"
        ),
        Room.CAFETERIA, List.of(
            "Hey! Today, I'll have...actually, is there anything you reccommend that's vegetarian?",
            "Sorry, can I get a little more time to look at the options?",
            "I don't like your food. I'd like something prepackaged."
        ),
        Room.CLASSROOM, List.of(
            "Hey Mr. White! I'm sorry but I'm not sure I'll be able to do the assignment by tomorrow. Do you think I could get an extension just until Monday?",
            "May I please get an extension on this? I have a lot going on this week.",
            "No, I can't do this assignment."
        )
    );

    public static List<String> getOptions(Room room) {
        return dialogues.getOrDefault(room, List.of("..."));
    }
}