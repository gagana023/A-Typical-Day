---

## layout: default\_dl parent: Design Document title: Phase 3 \- Designing nav\_order: 12

# **Proposal Phase 3**

The following section includes a detailed design document (Something every engineer will spend a lot of time on. Often the more time spent here the less time it takes to get the project work complete as you will save time by avoiding creating project elements that you may just have to throw out or redo)

## [High Level Architecture \- Summary](https://nchs-cs.github.io/advanced-topics/final-project/doc_phase3.html)

This section will most likely be hard to create.

See [this document](https://drawio-app.com/blog/3-diagrams-every-computer-science-student-should-know) on some high level architecture diagrams that you can consider creating. We'll review and go over some of these in class as well. Feel free to add other **standard** software design diagrams as appropriate to your project.

Ask yourself, “What is the difficult part about the project? How will I solve that problem?” If there is nothing difficult about the project, then the project may not be complex enough. If you don’t know how to solve the problem, ask, “How will I learn to solve that problem?” Explain all of that here.

Lastly, consider what classes you will create, their roles & responsibilities, how your classes will interface with one another, where data will come from, and what data structures will be used. If there are lots of data sources and paths, making a diagram can help add clarity. Many projects will not require a Data Flow Diagram.

**High Level Architecture:** The system is structured around the central GameEngine class that manages game flow and coordinates player, room, NPC, and dialogue interaction through the use of dialogues. The GameEngine initializes the game, loads the rooms, updates animations, and manages screen transitions. When the game begins, GameEngine begins with a StoryIntro sequence and sets the player’s starting position using the StartPos class.

Characters in the game are organized using inheritance hierarchy. The Person class serves a base class that stores shared attributes such as position, sprite, age and gender. The **User** class represents the player and the NPC  class represents non-player characters including teachers, classmates, and social groups. NPCs store dialogue using the NPCDialogue class. 

**DialogueEngine** manages conversations between players and NPCs. Dialogues make an impact on the social battery which is stored in Stats and represented in Bars. Rooms are presented using **Room** class and its subclasses. 

**One of the more complex parts of this project is designing the dialogue interaction system. The game must handle multiple dialogue choices, update player statistics, and trigger different NPC responses. This challenge is addressed by separating dialogue storage (NPC Dialogue), dialogue processing (DialogueEngine), and player input (playerDialogueChooser). This structure helps keep the dialogue system organized and scalable.** 

## Detailed Schedule

Revise your weekly milestones with detailed tasks for each week (minimum 4 tasks / week \- 2 per team member).

Include a link to your detailed task list here as well as the markdown version inline (make sure to paste the markdown after you convert your document to markdown)

Create your task list from [this template](https://docs.google.com/spreadsheets/d/1F2ba3ekH2hg-wLWOCXHkyzi8Q8jizi4QHmhfRdichcU/edit?usp=sharing) (You may modify but you should keep this as the minimum required information for a task list).

You must include your phase 3 detailed task list in this document as markdown. (See below for an example; you can convert t google sheet to markdown using this tool here: [https://tabletomarkdown.com/convert-spreadsheet-to-markdown/](https://tabletomarkdown.com/convert-spreadsheet-to-markdown/))

This is an ordered list of major pieces of functionality created in an intentional order to help you incrementally create a larger project that works. Each deliverable is a significant piece of functionality that should be completed before the next deliverable starts. While it is often true that one deliverable must be delivered before another can be accomplished, deliverables do not have to have dependencies on one another. This means that two deliverables can be developed in parallel by different members of a team.

Oftentimes we will discover more deliverables along the way. This is fine. It is difficult (sometimes impossible) to know everything at the start of a project. Discovered work will be added to the Task List for following weeks, or as zero initial time estimates for the current week.

You should take your task milestones and add tasks for each milestone that are well defined, with time estimates.

## Diagrams

At a minimum you must create a class diagram (and details) and sequence diagram. You could consider using [Mermaid](https://mermaid.live/) to create them (you do not need an account; be sure to save your mermaid text though so you don't lose your work); search up what you can do at [this link\!](https://mermaid.js.org/intro/). There are also extensions for visual studio that you can try out and use for creating these diagrams using mermaid chart syntax (I personally like using [Markdown Preview Mermaid](https://marketplace.visualstudio.com/items?itemName=bierner.markdown-mermaid) so you can include mermaid content directly inline in your markdown document). 

**Class Diagram:** This diagram illustrates the static structure of a system by showing its classes, attributes, operations, and the relationships among objects. It provides a clear blueprint of the system's architecture, essential for understanding how different components interact. There is an excellent reference and example on how to create class diagrams here. You can also read more about them on GeeksForGeeks.  
classDiagram  
    class User {  
        \-String userId  
        \-String name  
        \-String phoneNumber  
        \+register()  
        \+login()  
        \+updateProfile()  
    }

    class Rider {  
        \-PaymentMethod\[\] paymentMethods  
        \+requestRide()  
        \+cancelRide()  
        \+addPaymentMethod()  
    }

    class Driver {  
        \-String licenseNumber  
        \-Vehicle vehicle  
        \-double rating  
        \+acceptRide()  
        \+updateStatus()  
        \+endRide()  
    }

    class Ride {  
        \-String rideId  
        \-Location pickupLocation  
        \-Location dropoffLocation  
        \-double cost  
        \-RideStatus status  
        \+calculateFare()  
        \+updateStatus()  
    }

    class Vehicle {  
        \-String plateNumber  
        \-String model  
        \-int year  
        \+getDetails()  
    }

    class PaymentSystem {  
        \+processPayment()  
        \+refundPayment()  
    }

    User \<|-- Rider : inheritance  
    User \<|-- Driver : inheritance  
    Driver "1" \-- "1" Vehicle : has \>  
    Rider "1" \-- "\*" Ride : requests \>  
    Driver "1" \-- "\*" Ride : accepts \>  
    Ride "1" \-- "1" PaymentSystem : uses \>

# **Our Class Diagram**

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
        -Array various_Tasks
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


# **Our Sequence Diagrams**

Story Part

sequenceDiagram  
    actor U as User  
    participant GE as GameEngine  
    participant SI as StoryIntro  
    participant SP as StartPos

    U-\>\>GE: startGame()  
    GE-\>\>SI: loadStory()  
    SI-\>\>U: showTitleScreen()  
    SI-\>\>U: showAlarmScreen()  
    SI-\>\>U: playStoryIntro()  
    GE-\>\>SP: setStartPosition()

Moving between Rooms

sequenceDiagram  
    actor U as User  
    participant GE as GameEngine  
    participant R as Room

    U-\>\>GE: moveTo(Room)  
    GE-\>\>R: loadRoom()  
    R-\>\>GE: confirmRoomLoaded()

Generic Convo w NPCs

sequenceDiagram  
    actor U as User  
    participant R as Room  
    participant N as NPC  
    participant T as Tasks  
    participant B as Bars

    U-\>\>N: interact()  
    N-\>\>U: respondToPlayer()  
    U-\>\>T: checkOffTask()  
    U-\>\>B: updateSocialBar()

Teacher Conversations

sequenceDiagram  
    actor U as User  
    participant T as Teacher  
    participant B as Bars  
    participant Task as Tasks

    U-\>\>T: approachTeacher()  
    U-\>\>T: askExtension()  
    alt extension  
        Task-\>\>U: checkOffTask()  
        U-\>\>B: increaseSocialBar(\+)  
    else noExtentsion  
        U-\>\>T: yell()  
        T-\>\>U: condescending()  
        U-\>\>B: decreaseSocialBar(\-)  
    end  
    B-\>\>U: taskFail()

Bully Conversation

sequenceDiagram  
    actor U as User  
    participant C as Classmate  
    participant B as Bars

    U-\>\>C: interact()  
    C-\>\>U: bullying()  
    alt respondWell  
        B-\>\>U: increaseSocialStanding()  
    else respondPoorly  
        C-\>\>U: bullying()  
        B-\>\>U: decreaseSocialStanding()  
    end

Study Group Conversations

sequenceDiagram  
    actor U as User  
    participant SG as StudyGroup  
    participant B as Bars

    U-\>\>SG: requestJoin()  
    SG-\>\>U: respondToPlayer()  
    alt allowed  
        SG-\>\>U: allowJoin()  
        U-\>\>B: increaseSocialStanding()  
    else denied  
        U-\>\>SG: panic()  
        SG-\>\>U: ignorePlayer()  
        U-\>\>B: decreaseSocialStanding()  
    end

Eating Group Conversations

sequenceDiagram  
    actor U as User  
    participant EG as EatingGroup  
    participant B as Bars

    U-\>\>EG: requestJoin()  
    EG-\>\>U: respondToPlayer()  
    alt allowed  
        EG-\>\>U: joinGroup()  
        U-\>\>B: increaseSocialBar()  
    else denied  
        U-\>\>EG: panic()  
        EG-\>\>U: ignorePlayer()  
        U-\>\>B: decreaseSocialBar()  
    end

Task CheckOff Process

sequenceDiagram  
    actor U as User  
    participant TL as TaskList

    U-\>\>TL: checkOffTask()  
    alt isTaskDone \== true  
        TL-\>\>U: markDone()  
        U-\>\>TL: allTasksDone()  
    else isTaskDone \== false  
        TL-\>\>U: taskFailed()  
    end

Update Bars

sequenceDiagram  
    actor U as User  
    participant B as Bars

    alt goodResponse  
        U-\>\>B: increaseSocialBar()  
        U-\>\>B: increaseSocialStanding()  
        B-\>\>U: updateStats()  
        B-\>\>U: updateStats()  
    else badResponse  
        U-\>\>B: decreaseSocialBar()  
        U-\>\>B: decreaseSocialStanding()  
        B-\>\>U: updateStats()  
        B-\>\>U: updateStats()  
    end

Info Stuff Navigation

sequenceDiagram  
    actor U as User  
    participant M as Menu  
    participant MS as MenuScreen  
    participant S as Settings  
    participant HI as Help  
    participant II as Important\_Info

    U-\>\>M: openMenu()  
    M-\>\>U: displayOptions()

    U-\>\>M: selectSettings()  
    M-\>\>MS: accessSettings()  
    MS-\>\>S: openSettings()  
    S-\>\>U: showSettings()

    U-\>\>M: selectHelp()  
    M-\>\>MS: accessHelp()  
    MS-\>\>HI: openHelp()  
    HI-\>\>U: displayHelp()

    U-\>\>M: selectImportantInfo()  
    M-\>\>MS: accessImportantInfo()  
    MS-\>\>II: openInfo()  
    II-\>\>U: displayInfo()

Animations 

sequenceDiagram  
    actor GE as GameEngine  
    participant SP as StartPos  
    participant R as Room  
    participant A as Animations

    GE-\>\>SP: setStartPosition()  
    SP-\>\>GE: isPlayerReady()  
    GE-\>\>R: loadRoom()  
    R-\>\>A: startAnimation()  
    A-\>\>GE: updateAnimation()

Dialogue:

sequenceDiagram  
    actor U as User  
    participant N as NPC  
    participant ND as NPCDialogue  
    participant DE as DialogueEngine  
    participant UD as playerChooserDialogue

    U-\>\>N: interact()  
    N-\>\>ND: requestDialogue()  
    ND-\>\>DE: pickNPCResponse()  
    U-\>\>DE: requestDialogue()  
    DE-\>\>UD: chooseDialogueOptions()  
    UD-\>\>U: returnSelectedOption()

**Diagram Elements:**

Boxes: Classes with their properties and methods  
Upper section: Class name  
Middle section: Properties/attributes with \- for private and \+ for public  
Lower section: Methods/operations  
Arrows:  
\<|--: Inheritance relationship (Rider and Driver inherit from User)  
\--: Association relationship with navigation direction (\>)  
"1" and "\*": Multiplicity/cardinality (1-to-1 or 1-to-many) {% endtab %}  
{% tab diagrams Sequence Diagram %} Sequence Diagram: Depicting the sequence of messages exchanged among objects, sequence diagrams detail how operations are carried out, including the order of interactions. They are instrumental in modeling the dynamic behavior of a system, especially for complex processes. You can also read more about them on GeeksForGeeks.

sequenceDiagram  
    actor R as Rider  
    participant App as RideShareApp  
    participant S as RideMatchingSystem  
    participant D as Driver  
    participant P as PaymentSystem

    R-\>\>App: requestRide(location, destination)  
    App-\>\>S: findAvailableDrivers(location)  
    S-\>\>D: notifyRideRequest()  
    D-\>\>S: acceptRide()  
    S-\>\>App: confirmRideMatch()  
    App-\>\>R: displayDriverInfo()

    Note over R,D: Driver arrives at pickup location

    D-\>\>App: startRide()  
    App-\>\>R: notifyRideStarted()

    Note over R,D: Ride in progress

    D-\>\>App: endRide(destination)  
    App-\>\>P: processFare(rideDetails)  
    P-\>\>App: confirmPayment()  
    App-\>\>R: requestRating()  
    R-\>\>App: submitRating(stars)  
    App-\>\>D: updateRating()

**Diagram Elements:**

Actors: External entities (Rider)  
Participants: System components (App, Driver, etc.)  
Arrows with Text: Messages between participants  
\-\>: Synchronous message (sender waits for response)  
\--\>: Response/return message  
\-\>\>: Asynchronous message (sender doesn't wait)  
Notes: Additional context or explanations  
Vertical Lines: Lifelines showing object existence over time {% endtab %}  
{% tab diagrams Flow Chart Diagram %} Flow Chart Diagram: A flowchart diagram in a detailed design document shows the step-by-step logic of a process or method. It uses shapes like diamonds (decisions) and rectangles (actions) to represent control flow. Flowcharts are ideal for illustrating branching paths, loops, or procedural algorithms. They help readers visualize how specific game features or functions work internally. Use them when the logic is too complex for plain text or pseudocode alone. You can also read more about them on GeeksForGeeks. While not required you will find that explaining any algorithms or complex calculations easier with a flow chart. Consider using these where time based sequences are not key to the procedure.

Click to view Mermaid code  
Flowchart Diagram

Diagram Elements:

Rounded Rectangles with Square Brackets \[\]: Process steps/actions  
Diamonds with Curly Braces {}: Decision points with multiple paths  
Rounded Capsules with Parentheses (\[\]): Start/End points  
Arrows: Flow direction  
Plain arrows: Standard flow  
Labeled arrows: Conditional paths (e.g., "Yes", "No")  
Vertical Arrangement: Time/sequence flows from top to bottom  
Horizontal Branches: Alternative paths based on decisions {% endtab %}  
{% tab diagrams Others %} Depending on the features and/or scope of your project, you may want to include other kinda of diagrams as well. Listed below are a few examples, along with some use cases for them. Feel free to look other ones up\!

Data Flow Diagrams: Shows how data moves through your application, identifying inputs, processes, outputs, and storage locations. It helps clarify data dependencies and processing requirements. You can read more about them on GeeksForGeeks.

When to use: For applications with complex data processing, especially those dealing with multiple data sources, transformations, and storage systems. Ideal for:

Data-intensive applications (analytics platforms, ETL systems)  
Applications with multiple external interfaces (payment gateways, APIs)  
Systems with complex business logic processing  
Here's an example for a customer buying something from a store. (from Canva) Data Flow Diagram

State Diagrams: Shows the different states an object can be in and how events trigger transitions between these states. They're particularly valuable when behavior changes significantly based on state. You can read more about them on GeeksForGeeks.

When to use: For systems with distinct states and transitions, such as:

Game development (character states, game progression)  
Workflow applications (document approval processes)  
Systems with complex life cycles (order processing, user account management)  
Here's an example for a Bank ATM. (from BizzDesign) State Diagram

{% endtab %}

{% endtabs %}

## Class Roles & Responsibilities

Building on the diagrams you created in the last section you should expand on the details for as many of your classes that you can identify and their roles & responsibilities. It could be that that will be enough. If you cannot list off at least three classes, the project is not complex enough.

Here's an example:

| Class name | Roles & Responsibilities |
| :---- | :---- |
| `GameController` | \- Initializes and manages the overall game state \- Handles the game loop (update, render cycle) \- Coordinates communication between UI, game logic, and data storage \- Manages transitions between different game screens |
| `Player` | \- Stores player attributes (position, health, inventory, etc.) \- Handles player input and movement logic \- Contains methods for player actions (attack, use item, etc.) \- Tracks player progression and statistics |
| `MapGenerator` | \- Creates and manages the game world/levels \- Implements procedural generation algorithms for terrain \- Places objects, enemies, and collectibles on the map \- Handles map persistence and loading |
| `EnemyAI` | \- Controls enemy behavior and decision-making \- Implements pathfinding to navigate toward the player \- Manages enemy attacks and special abilities \- Handles enemy spawning and difficulty scaling |
| `UIManager` | \- Renders all user interface elements \- Handles UI input and interaction \- Manages menus, HUD, and dialog systems \- Updates UI based on game state changes |

| Class name | Roles & Responsibilities |
| :---- | :---- |
| `Person*` | Serves as base class for all characters in game Stores character attributes including position, sprite, age, gender Provides properties used by BOTH THE player and NPC Class. |
| `User*` | Represents player-controlled character in game Handles actions such as movement, selection, and more Stores player’s active tasks and progress throughout game Tracks task success or failure and triggers corresponding gameplay decision |
| `NPC*` | \- Represents non-player character that are in the game \- Handles autonomous NPC behaviours such as random movement   \- Initiates dialogue interactions between player \- Responds to player actions and triggers dialogue events |
| `Teacher` | Represents teacher NPCs throughout game Tracks player trust level with teacher Evaluates players actions and determines teacher reaction Handles interactions such as asking for extensions and approaching teacher |
| `Classmate` | Represents peer NPCs in the game Tracks bullying and respect levels toward player Controls aggressive and bullying during interaction Respond to player’s social choices |
| `StudyGroup` | Represents study group and NPC interaction Determines whether player is allowed to join group Evaluates player behavior and social standing Handles group interactions and respond |
| `EatingGroup` | Represents social groups in areas like the cafeteria and library Triggers group behaviours such as panic reactions |
| `Tasks` | Stores the list of tasks assigned to the player Tracks completion status of each task Determines whether all tasks haVe been completed Updates task status when a player completes or fails a task |
| `Bars` | Manages visual representation of player statistic Displays player’s social battery and social standing bars Updates UI elements when statics change |
| `Stats` | Stores player social statistics such as social battery and social standing Updates values based on player choices and interactions Track their social performance throughout game  |
| `MenuScreen*` | Manages settings, importantInfo and help class Keeps the game personalized Controls visibility of menu screens Handles player navigation through menu selections |
| `Settings` | Stores game options Allows players to modify interface or game settings |
| `Important_Info` | Displays key information relevant Provides key information about why this game is necessary and what it teaches and the awareness it hopes to bring.  |
| `Help` | Provides instructions and guidance Displays explanations of game mechanics Explains controls, objectives and game mechanics |
| `Menu` | Represents the main navigation menu Allows the player to open diff interface screens such as settings or help Provides access to gameplay options and menu navigation |
| `Room*` | Manages rooms in which user has tasks (office, library, cafeteria, classroom) Representing locations that the player can explore Stores room properties, size background name Handles loading and initialization of room assets and entities, NPCs and tasks |
| `Office` | Represents teacher office environment Hosts interactions with teacher NPCs Provides location for academic-related dialogue events |
| `Group` | Handles both the Library and Cafeteria Room Handles both because they share many properties Both use group settings which has to include group dialogue mechanic Both include specific type of setting that can use similar code |
| `Library` | Represents study room in game environment Host interaction with study group and NPCs Provides setting for academic or quiet interactions |
| `Cafeteria` | Represents cafeteria social environment Hosts eating group and NPC encounter Provides setting for group-based dialogue events |
| `Classroom` | Represents classroom environment where lessons and tasks may occur Hosts interactions with teachers and classmates Serves as location for academic gameplay events |
| `TaskList` | Has a database of all possible tasks Ensures user doesn’t get the same list of tasks over and over |
| `GameEngine*` | Manages the beginning of the game Manages JavaFX stage and scene and starts the game |
| `StoryIntro*` | Manages introductory story sequence at the very beginning of game Displays the title screen and alarm screen Plays the story introduction Controls transitions between the intro scenes.  |
| `Title` | Displays the game’s title screen Provides initial visual introduction to game Waits for the player to start game or continue to next screen |
| `Alarm` | Displays the alarm screen event that begins the story sequence Triggers transition from title screen to **story introduction** Provides visual and audio interaction at beginning of gameplay |
| `Story` | Manages narrative content presented during game introduction Displays story text and scenes Controls transitions between story segments during the introduction |
| `StartPos` | Determines player’s starting position in the game world Initializes players location when the game begins Ensures player is correctly placed within starting room |
| `Animations` | Handles visual animations used sequences Updates character or object animations for **game loop** Smooth transitions in the animation states |
| `DialogueEngine` | Manages flow and execution of dialogues Starts dialogue sessions Processes player dialogue choices and determines outcomes Updated player statistics such as social battery and social standing Ends dialogue sessions once interactions are complete |
| `NPCDialogue` | Stores dialogue lines associated with specific NPC Allows random or context-based dialogue selection for NPC conversations |
| `playerDialogueChooser` | Displays dialogue options to the player Handles player input when selecting dialogue responses Passes selected dialogue choices back to dialogue |

## Important Algorithms/Functionality

Clearly documenting key algorithms is essential for ensuring a well-structured and efficient implementation of your project. This section should highlight any complex or critical algorithms that will be developed, such as game loops, AI decision-making, pathfinding, data processing, or physics simulations.

**Why is this important?**

- It helps identify potential challenges early, allowing for better planning and problem-solving before coding begins.  
- Ensures consistency in implementation, especially when working in a team, by providing a shared understanding of how key functionalities will operate.  
- Serves as a reference throughout development, reducing confusion and making debugging more efficient.  
- Helps prevent scope creep by defining the complexity of features in advance.

**How detailed should this section be?**

- Provide a high-level explanation of what the algorithm does and why it’s needed.  
- Include a brief outline of the logic or steps involved (pseudocode or flowcharts can be useful but should remain concise).  
- If applicable, mention key considerations like efficiency, potential bottlenecks, or alternative approaches.  
- Avoid excessive detail—this is not a full implementation but rather a roadmap for development.

This level of detail provides enough guidance without overwhelming the design document with unnecessary complexity.

## Approval

You must receive teacher approval to begin coding.  
flowchart TD  
     A\[Start Pathfinding\] \--\> B{Is Goal Reached?}  
     B \-- Yes \--\> G\[End Pathfinding\]  
     B \-- No \--\> C\[Get Walkable Neighbors of Current Node\]  
     C \--\> H\["Score Neighbors\<br\>& select lowest total"\]  
     H \--\> I{Recalculation Needed?}  
     I \-- No \--\> J\[Move to Next Node\]  
     I \-- Yes \--\> K\[Recalculate Limited Path\]  
     K \--\> J  
     J \--\> B

Dialogue Logic Outline

1) User interacts with NPC  
2) NPC sends dialogue request to NPCDialogue  
3) NPCDialogue picks a dialogue from DialogueEngine  
4) DialogueEngine shows NPC response to User  
5) User sends dialogue request to DialogueEngine  
6) DialogueEngine chooses a Dialogue option and sends to playerDialogueChooser  
7) playerDialogueChooser returns selected option and sends it back to User

Bar Update Logic

1) Player interacts with NPC or group  
2) Interaction result is determined  
   1) If user picked a good response, the socialStanding/socialBar go up  
      1) If social battery goes up, screen is more clear and responses stay on screen for more time  
   2) If user picked a bad response, the socialStanding/socialBar go down  
      1) If social battery goes down, screen gets darker and responses shake, words are mixed when you click the response, the response stays for less amount of time, etc  
3) Bars class updates the socialStanding and the socialBar  
4) Player stats are updated

Task Completion Logic

1) Task is assigned to Player  
   1) Task pulled from TaskList randomly  
2) Player tries to complete required action  
   1) If works, the task is checked off successfully  
   2) If doesn’t work, task failed notification comes up  
3) Updates the task list based on result  
4) At the very end, if all tasks have been attempted, it takes you home to stats screen

Room Navigation Logic

1) Player selects a room  
2) Room loads  
3) NPCs spawn  
4) Player can interact based on tasks