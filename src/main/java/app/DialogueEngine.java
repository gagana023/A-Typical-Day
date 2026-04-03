package app;

import java.util.List;
import java.util.Map;

public class DialogueEngine {

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
            "What's on the menu today?",
            "Is there a vegetarian option?",
            "Can I get extra napkins?"
        )
    );

    public static List<String> getOptions(Room room) {
        return dialogues.getOrDefault(room, List.of("..."));
    }
}