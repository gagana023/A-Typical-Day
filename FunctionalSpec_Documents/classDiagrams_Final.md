```mermaid
classDiagram

class HelloWorld {
  +Stage stage
  +Scene scene
  +User user
  +Label task1
  +Label task2
  +Label task3
  +ProgressBar socialBatteryBar
  +ProgressBar socialStandingBar

  +start(Stage stage) 
  +main(String[] args) 
  +getStage() 
  +updateStatsBars() 
  +updateTasks(Label t1, Label t2, Label t3) 
  +handleChoice(int option, String taskId, Stage stage) 
}

class User {
  -RoomManager roomManager
  -PlayerInput playerInput
  -Canvas topL
  -Canvas botL
  -Canvas topR
  -Canvas botR
  -MovementController movementController
  -Canvas canvas
  -AnimationTimer timer
  -boolean canEnter
  -RoomTransitioner roomTransitioner
  -PlayerStats stats
  -CollisionChecker collisionChecker
  -PlayerAnimation playerAnimation
  -Player player

  +User()
  +getInAddAllForm() 
  +resume(Scene scene) 
  +connect(Scene scene) 
  +changeStats(int optionType) 
  +getHealth() 
  +getSocial() 
  +isColliding(Canvas box) 
  +setRooms(NPC topL2, NPC botL2, NPC topR2, NPC botR2, Stage stage) 
  +stopMovement() 
  +start() 
  +stop() 
  +getPlayer() 
  +setCoordinates(double x, double y) 
  +stayInBoundaries(Scene scene) 
}

class Player {
  -PlayerPosition position
  -PlayerStats stats
  -Canvas canvas

  +Player(Canvas canvas)
  +updateCanvasPosition() 
  +getPosition() 
  +getCanvas() 
  +getStats() 
  +setCoordinates(double x, double y) 
  +stayInBoundaries(Scene scene) 
}

class PlayerPosition {
  -double x
  -double y

  +PlayerPosition(double startX, double startY)
  +move(double dx, double dy, double speed) 
  +stayInBoundaries(Scene scene, double canvasWidth, double canvasHeight) 
  +setCoordinates(double newX, double newY) 
  +setX(double newX) 
  +setY(double newY) 
  +getX() 
  +getY() 
}

class PlayerStats {
  -double health
  -double social

  +changeStats(int optionType) 
  +getHealth() 
  +getSocial() 
  -clamp(double value) 
  +isGameOver() 
}

class PlayerInput {
  -boolean wPressed
  -boolean aPressed
  -boolean sPressed
  -boolean dPressed

  +connect(Scene scene) 
  +stopMovement() 
  +getHorizontalMovement() 
  +getVerticalMovement() 
}

class MovementController {
  -double SPEED
  -PlayerInput input

  +MovementController(PlayerInput input)
  +update(Player player, Scene scene) 
  +stop() 
}

class PlayerAnimation {
  -int FRAME_DELAY
  -Sprite sprite
  -Canvas canvas
  -int frameTick

  +PlayerAnimation(String spritePath, int rows, int columns, int frameCount)
  +getCanvas() 
  +updateAnimation(boolean moving) 
  +renderFrame() 
}

class Sprite {
  -Image sheet
  -int cols
  -int spriteCount
  -double frameWidth
  -double frameHeight
  -double spriteWidth
  -double spriteHeight
  -double x
  -double y
  -List~Integer~ animationSequence
  -int sequenceIndex

  +Sprite(String resourcePath, int rows, int cols, int spriteCount)
  +Sprite(Image sheet, int rows, int cols, int spriteCount)
  +getSpriteCount() 
  +setFrameSize(double width, double height) 
  +getFrameWidth() 
  +getFrameHeight() 
  +setAnimationSequence(List~Integer~ sequence) 
  +nextFrame() 
  +setPosition(double x, double y) 
  +getXPosition() 
  +getYPosition() 
  +renderCurrent(GraphicsContext gc) 
  +render(GraphicsContext gc, int spriteIndex, double x, double y, double destWidth, double destHeight) 
}

class NPC {
  -Sprite sprite

  +NPC(String spritePath)
  +setPosition(double x, double y) 
}

class Classmate {
  +Classmate(String spritePath)
}

class CollisionChecker {
  +isColliding(Canvas player, Canvas target, double playerX, double playerY) 
}

class Room {
  +getRoot(Stage stage) 
}

class JoinGroupHere {
}

class Office {
  +getRoot(Stage stage) 
}

class Library {
  +getRoot(Stage stage) 
}

class Cafeteria {
  +getRoot(Stage stage) 
}

class Classroom {
  +getRoot(Stage stage) 
}

class RoomManager {
  -Stage stage
  -boolean enteringOffice
  -boolean enteringCafeteria
  -boolean enteringClassroom
  -boolean enteringLibrary

  +RoomManager(Stage stage)
  +resetEntries() 
  +enterOffice() 
  +enterClassroom() 
  +enterLibrary() 
  +enterCafeteria() 
  -openRoom(Room room) 
}

class RoomTransitioner {
  -Canvas topL
  -Canvas topR
  -Canvas botL
  -Canvas botR
  -RoomManager roomManager
  -boolean canEnter
  -CollisionChecker collisionChecker

  +RoomTransitioner(Canvas topL, Canvas topR, Canvas botL, Canvas botR, Stage stage)
  +check(User user) 
  -isColliding(User user, Canvas box) 
  -enter(String roomName) 
  +reset() 
}

class DialogueUI {
  -Button option1
  -Button option2
  -Button option3
  -StackPane response1
  -StackPane response2
  -StackPane response3

  +DialogueUI(List~String~ userOptions, List~String~ npcOptions)
  -createResponseBubble(String responseText) 
  -setPositions() 
  +addToRoot(AnchorPane root) 
  +showOptions() 
  +hideOptions() 
  -startOptionFades() 
  +setOption1Action(Runnable action) 
  +setOption2Action(Runnable action) 
  +setOption3Action(Runnable action) 
}

class NPCDialogue {
  <<static>>

  +getOptions(Room room) 
}

class UserDialogueEngine {

  +getOptions(Room room) 
}

class Tasks {
  -List~Task~ allTasks
  -List~Task~ activeTasks

  +chooseRandomTasks(int amount) 
  -conflictsWithActiveTasks(Task newTask) 
  -tasksConflict(String taskA, String taskB) 
  -samePair(String taskA, String taskB, String first, String second) 
  +getActiveTasks() List~Task~
  +completeTask(String id) 
  +isTaskActive(String id) 
}

class Task {
  -String id
  -String description
  -boolean done

  +Task(String id, String description)
  +getId() 
  +getDescription() 
  +isDone() 
  +complete() 
}

class Panic {
  -int x
  -int y
  -Stage primaryStage

  +shakeStage() 
  +darkenStage() 
  +setPrimaryStage(Stage primaryStage) 
}

class Stats {
  +buildPrototypeHomeAndStats(Stage stage) 
  +getMenu() 
  +createSection(String text) 
  +createBarSection(String text) 
}

class Help {
  -TextArea helpArea
  -String DEFAULT_TEXT

  +Help(Stage stage)
  +setHelpText(String text) 
  +appendHelpText(String text) 
  +getHelp(Stage stage) 
}

class StoryIntro {
  -SequentialTransition storySequence

  +build(Stage stage, Scene gameScene, User user) 
  +play() 
}

class GameOver {
  +getScene(Stage stage) 
}

class MenuScreen {
}

Room <|-- Office
Room <|-- Library
Room <|-- Cafeteria
Room <|-- Classroom
Room <|-- JoinGroupHere

JoinGroupHere <|-- Library
JoinGroupHere <|-- Cafeteria

NPC <|-- Classmate
Canvas <|-- NPC

Tasks *-- Task

User *-- Player
User *-- PlayerInput
User *-- PlayerAnimation
User *-- MovementController
User *-- CollisionChecker
User *-- RoomTransitioner
User *-- RoomManager

Player *-- PlayerPosition
Player *-- PlayerStats

PlayerAnimation *-- Sprite
NPC *-- Sprite

RoomTransitioner *-- RoomManager
RoomTransitioner *-- CollisionChecker

HelloWorld --> User
HelloWorld --> Tasks
HelloWorld --> Stats
HelloWorld --> Help
HelloWorld --> StoryIntro
HelloWorld --> Office
HelloWorld --> Library
HelloWorld --> Classroom
HelloWorld --> Cafeteria

Office --> DialogueUI
Library --> DialogueUI
Classroom --> DialogueUI
Cafeteria --> DialogueUI

Office --> NPCDialogue
Library --> NPCDialogue
Classroom --> NPCDialogue
Cafeteria --> NPCDialogue

Office --> UserDialogueEngine
Library --> UserDialogueEngine
Classroom --> UserDialogueEngine
Cafeteria --> UserDialogueEngine

PlayerStats --> Panic

Stats --> HelloWorld
Help --> HelloWorld

StoryIntro --> Help
StoryIntro --> User

RoomManager --> Room

    ```