
```mermaid
classDiagram
    Person <|-- User
    Person <|-- NPC
    NPC <|-- Teacher
    NPC <|-- Classmate
    NPC <|-- StudyGroup
    NPC <|-- EatingGroup
    NPC -->NPCDialogue
    User --> Tasks
    User --> Bars
    User --> Stats
    MenuScreen <|-- Settings
    MenuScreen <|-- Important_Info
    MenuScreen <|-- Menu
    MenuScreen <|-- Help
    Person : +int age
    Person : +String gender
    Person: -double xPosition
    Person: -double yPosition
    Person: +Sprite sprite
    NPC : -NPCDialogue dialogue
    NPC: -showResponse()
    NPC: +startDialogue()
    NPC: +reactToPlayer()
    NPC: +requestDialogue()


    Room <|-- Office
    Room <|-- Group
    Room <|-- Classroom
    DialogueEngine --> playerDialogueChooser
    DialogueEngine --> NPCDialogue
    DialogueEngine: +startDialogue()
    DialogueEngine: +processChoice()
    DialogueEngine: +updateStats()
    DialogueEngine: +batteryImpact()
    DialogueEngine: +standingImpact()
    DialogueEngine: +endDialogue()
    DialogueEngine: +provideResponse()




    NPCDialogue: -ArrayList<String>npcDialogue
    NPCDialogue: +getDialogue()
    NPCDialogue: +getRandomDialogue()




    playerDialogueChooser: +displayChoices()
    playerDialogueChooser: +getPlayerChoice()
   
   
    Group <|--Cafeteria
    Group<|--Library


    StoryIntro --> Title
    StoryIntro --> Alarm
    StoryIntro --> Story
   
    class StartPos{
        setStartPosition()
    }
    class MenuScreen{
        +show()
        +hide()
        +selectMenu()
        +selectSettings()
        +selectHelp()
        +selectImportantInfo()
    }
    class playerDialogueChooser{


    }
   
    class DialogueEngine{


    }
    class NPCDialogue{
    }


    class Settings{
        +openSettings()
    }


    class Menu{
      +openMenu()
      +displayOptions()
      +showSettings()
      +displayHelp()
      +displayInfo()


    }


    class Important_Info{


    }


    class Help{


    }


    class User{
      +move()
      +select()
      +interact()
      +respondToPlayer()
      -ArrayList<Task> tasks
      +taskFail()
      +taskSucceed()
      
    }
    class NPC{
      +random_Movement()
      +bullying()
      +allowJoin()
      +ignorePlayer()
      +joinGroup()
    }


    class Bars{
        -int socialBar
        -int socialStanding
        +decrease_socialBar()
        +decrease_socialStanding()
        +increase_socialBar()
        +increase_socialStanding()
        +getSocialBar()
        +getSocialStanding()
        +renderBar()
    }


    class Tasks{
      +boolean allTasksDone
      -boolean isTaskDone
      +checkOffTask()
      +markDone()
      +taskFailed()
     
    }


    class Stats{
        -double socialBattery
        -double socialStanding
        +increaseStanding()
        +decreaseStanding()
        +increaseBattery()
        +decreaseBattery()


    }


    class TasksList{
        -ArrayList<Task> various_Tasks
    }


    class Room{
        +double width
        +double height
        +ArrayList<NPC> npcs
        +ArrayList<Task> tasks
        +String roomName
        +Sprite background
        +loadRoom()
    }


    class Teacher{
      -int trustLevel
      +condescending()
      +evaluateStudent()
      +approachTeacher()
      +askExtension()
      +yell()
    }




    class Classmate{
      -int bullyLevel
      -int respectLevel
      +bullying()
      +interact()
    }


    class StudyGroup{
      +boolean allowJoin
      -ignorePlayer()
      +evaluatePlayer()
      +interact()
      +requestJoin()
      +panic()
    }








    class GameEngine{
        +Scene scene
        +Stage stage
        +startGame()
        +switchScene()
        +loadRoom()
        +updateGame()
        +updateAnimation()
        +enterRoom()
        +moveTo(Room)
        +confirmRoomLoaded()








    }
    GameEngine <|-- StartPos
    GameEngine <|-- StoryIntro
















    class StoryIntro{
       -loadStory()
       -nextScene()
 -showTitleScreen()
       -showAlarmScreen()
       -playStoryIntro()
    }








    class EatingGroup{
      +boolean allowJoin
      -ignorePlayer()
      +interact()
      +requestJoin()
      +panic()
     
    }


class Animations{
+startAnimation()
+updateAnimation()
}








    class Office{
    }
    class Library{
    }
    class Cafeteria{
    }
    class Classroom{
    }
    ```