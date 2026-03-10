
```mermaid
classDiagram
    Person <|-- User
    Person <|-- NPC
    Person : +int age
    Person : +String gender
    Person: -double xPosition
    Person: -double yPosition
    Person: +Sprite sprite

    NPC <|-- Teacher
    NPC <|-- Classmate
    NPC <|-- StudyGroup
    NPC <|-- EatingGroup
    NPC --> NPCDialogue
    NPC : -NPCDialogue dialogue
    NPC: -showResponse()
    NPC: +startDialogue()
    NPC: +reactToPlayer()
    NPC: +requestDialogue()
    class NPC{
      +random_Movement()
      +bullying()
      +allowJoin()
      +ignorePlayer()
      +joinGroup()
    }

    MenuScreen <|-- Settings
    class Settings{
        +openSettings()
    }
    MenuScreen <|-- Important_Info
    class Important_Info{
      -Text textInfo
    }
    MenuScreen <|-- Menu
    class Menu{
      +openMenu()
      +displayOptions()
      +showSettings()
      +displayHelp()
      +displayInfo()
    }
    MenuScreen <|-- Help
    class Help{
      -Text helpInfo
    }
    class MenuScreen{
        +show()
        +hide()
        +selectMenu()
        +selectSettings()
        +selectHelp()
        +selectImportantInfo()
    }

    Room <|-- Office
    Room <|-- Group
    Group <|--Cafeteria
    Group <|--Library
    Room <|-- Classroom
    class Room{
        +double width
        +double height
        +ArrayList<NPC> npcs
        +ArrayList<Task> tasks
        +String roomName
        +Sprite background
        +loadRoom()
    }

    class Office{
      +Teacher counselor
    }
    class Library{
      +StudyGroup studyGroup
    }
    class Cafeteria{
      +EatingGroup eatingGroup
    }
    class Classroom{
      +Teacher teacher
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

    class EatingGroup{
      +boolean allowJoin
      -ignorePlayer()
      +interact()
      +requestJoin()
      +panic()
    }

    User --> Tasks
    class Tasks{
      +boolean allTasksDone
      -boolean isTaskDone
      +checkOffTask()
      +markDone()
      +taskFailed()
    }

    class TasksList{
        -ArrayList<Task> various_Tasks
    }

    User --> Bars
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
    User --> Stats
    class Stats {
        -double socialBattery
        -double socialStanding
        +increaseStanding()
        +decreaseStanding()
        +increaseBattery()
        +decreaseBattery()
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

    NPCDialogue: -ArrayList<String>npcDialogue
    NPCDialogue: +getDialogue()
    NPCDialogue: +getRandomDialogue()

    DialogueEngine --> playerDialogueChooser
    DialogueEngine --> NPCDialogue
    DialogueEngine: +startDialogue()
    DialogueEngine: +processChoice()
    DialogueEngine: +updateStats()
    DialogueEngine: +batteryImpact()
    DialogueEngine: +standingImpact()
    DialogueEngine: +endDialogue()
    DialogueEngine: +provideResponse()

    playerDialogueChooser: +displayChoices()
    playerDialogueChooser: +getPlayerChoice()

    StoryIntro --> Title
    StoryIntro --> Alarm
    StoryIntro --> Story
    class StoryIntro {
       -loadStory()
       -nextScene()
       -showTitleScreen()
       -showAlarmScreen()
       -playStoryIntro()
     }
   
    class playerDialogueChooser{
      +chooseDialogueOptions()
      +returnSelectedOption()
    }
   
    class DialogueEngine{
      -ArrayList npcDialogue
      -ArrayList userDialogue
    }

    class NPCDialogue{
      +requestDialogue()
      +pickNPCResponse()
    }

    class StartPos{
        setStartPosition()
    }

    GameEngine <|-- StartPos
    GameEngine <|-- StoryIntro

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
    
    class Animations{
      +startAnimation()
      +updateAnimation()
    }

    ```