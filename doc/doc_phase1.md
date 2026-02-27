---

## layout: default\_dl parent: Design Document title: Phase 1 \- Outlining nav\_order: 10

# Proposal Phase 1

Download this template and save it to your github project.

## Invisible Disabilities Awareness Game

| Project Title |  |
| :---- | :---- |
| Name 1 | `Sreshta Namala` |
| Name 2 | `Gagana Bodala` |
| Name 3 | `Aadi Bharadwaj` |

## Project Description

This description will vary in length and detail. It could be as short as 4 sentences, or as long as 1 page. Target about 2-4 paragraphs long. Describe what the project does with enough detail that another developer could implement the project for you and get it pretty close. Sections below will allow another developer to get it very close\!

Provide a brief description of your project idea (2-3 sentences). Explain what your program will do and how it will use JavaFX for graphical development.

**Project Description:**  
Our project's job is to teach people about Invisible Disabilities. The player’s job is to make it through one day in the life of someone who has a disability and they are going through the day in a life at school. 

The player will need to go through social interactions, the way they will do this is by interacting with certain rooms. They will either encounter a friend, teacher, classmate, etc. They will be given certain questions or responses that they can say. There will be a timer on the screen, the longer you take, the more awkward it is for the person waiting for your answer. You can either choose an answer quickly (but this drains your social battery) or you can take your time, which will drain less social battery but might lose “social standing” points. The answer that is more “Socially acceptable” but will cause you to lose more social battery will be more blurry and will fade faster. The answer that you can take more time to think about will be there for longer but will lower your social battery, this will fade slower. Additionally, after each response, there are thoughts in your head that you are thinking about that is in response to the conversation, these thoughts constantly rewrite themselves to show how there are many thoughts in their head. 

You have a list of tasks that you have to finish. Once you finish the tasks, the game finishes. When you finish the list of tasks, you have to maintain your social battery and social standing. 

## Purpose & Motivation:

Why did you choose this project? What do you hope to learn or accomplish?

We chose this project because we wanted to spread awareness about how daily life can be for people with invisible disabilities. Parts of this project are based on real conversations and interactions witnessed and experienced in North Creek High School and Skyview Middle School.  
We hope to learn:  
\* how to animate sprites in a convincing manner (such as walk cycles being synced cleanly with player movement across the screen)  
\* how to make games appear 3D in JavaFX  
\* how to animate popups, and how to make them appear when the player interacts with an object  
We hope to accomplish increased empathy and understanding among our peers and anyone else who plays the game; the goal is to show how daily life can be when you don’t run on the same wavelength as everyone else. We’re trying to show the kinds of things people have to do and experience within the span of a single day.

## Project Features

You must create a Feature matrix table that shows a progression of each of your features. The difference between **Prototype features**, **Core features**, and **Stretch features** lies in their purpose, scope, and timing in product development.

{: .notice }

| Feature Category | Prototype Features | Core Features (For complete product) | Stretch Features (Future Enhancements) |
| :---- | :---- | :---- | :---- |
| User spatial movement | Can move left, right, and turn | Can move to various classrooms  | 3d world |
| Dialogue system | Static dialogue options | Have a couple different conditions happen based on answer | Analyze typeable responses with AI |
| Vision | Looking straight ahead | Looking straight ahead | Toggling between multiple perspectives |
| Environment design | Just have one hallway or one classroom | Make a map of events that students with disabilities have to go through | Continuous generation of the world (Minecraft, temple run, etc…) |
| NPCs | One or two static characters | Student or teacher NPCs with personalities | Use AI to recall past memories of user |
| Saving |  | Manual restart | Save and load progress |
| Homepage | Blank start screen | Start game, help menu | Cloud saves? |
| Audio |  | Ambient game music | Have jumbled dialogues in background, like in school |
| Multiplayer |  |  | Try to make it so that multiple people can play in the same environment? |
| Objective System |  | Gives you a list of objectives to complete while at school | Randomize tasks instead of having the same tasks? |

Read through each type of feature below:

### Prototype Features

- **Definition:** `Prototype` features focus on quick validation, while core features focus on delivering a complete, competitive, and sustainable product. We will work as quickly as possible to create our prototypes so we can get early feedback from peer user testing and adjust plans accordingly.  
- **Purpose:** To validate an idea, test market demand, and gather user feedback with minimal effort. You will use this step of development to try out some of your ideas on your fellow classmates *well before the final project due date.*  
- **Scope:** Limited to essential functionalities that solve the primary problem. Graphics may be placeholders and other similar shortcuts to validate the ideas of the project.  
- **Timing:** Released in the earliest stage of development to test hypotheses.  
- **Examples:** A ride-sharing app prototype might only allow users to request a ride and drivers to accept it (without payment integration or advanced route optimization).  
  {% endtab %}

Prototype:

- pixel rpg allowing player character to move through rooms (that is, you go through a doorway, and the part of the map you’re on changes)  
- fixed course (fixed classes to go through, like a class schedule)  
- static npcs (NOT randomly generated)  
  - player gets 2-3 options of responses to npc dialogue  
  - npc has one or two reactions to the response  
  - response by player can affect player battery/social standing by a fixed amount — nuanced increase/decrease should be completed in core  
- end-of-day  
  - “how was your day” from parent  
  - display basic game stats (battery and social standing)

{% tab features Core %}

### Core Features

- **Definition:** The fundamental and defining features of a product that provide its long-term value.  
- **Purpose:** To sustain and grow the product, enhancing user experience and differentiation. This should be your goal for the finished product.  
- **Scope:** The minimum functionality possible for peer feedback. Items may be hard coded at this point (such as preference) or other elements. May include a single scene for simplicity. Does not include optimizations, competitive advantages, and scalability.  
- **Timing:** Developed and refined after initial design is complete, often as part of iterative improvements.  
- **Examples:** In the same ride-sharing app, core features may include fare estimation, surge pricing, driver ratings, and in-app payments.  
  {% endtab %}

Core

- Blurriness increases as battery decreases  
- Response shrinkage increases as social standing decreases (more hesitation to say certain things)  
- Can move to various classrooms and down the hallway  
- Multiple responses for a question asked  
- The vision of the player \-- looks straight  
- Task list that players have to work through  
- Can interact with friends, teachers, bullies, strangers, maybe counselors, and more. 

{% tab features Stretch %}

### Stretch Features

- **Definition:** The extra features/goals that should only be implemented if time permits; they should be bonus and not fully necessary.  
- **Purpose:** To expand the app beyond its initial goals, furthering its differentiation.  
- **Scope:** A set of functions that are not essential for the project, treated as bonuses.  
- **Timing:** Developed after core features are fully completed and refined.  
- **Examples:** In the same ride-sharing app, stretch features may include subscription plans, paying via cryptocurrency, AI-recommended rides, and Augmented Reality navigation.

What `Stretch` features are you planning to implement if time permits? List the features that are bonus and completely unnecessary. These will be added only as time permits.

Stretch Features

- Multiplayer   
- Multiple environments  
  - Not just school  
- Multiple days  
- Implementing AI interactions between user and NPCs  
- Make a 3D world instead of flat, 2D  
- Continuous generation of the environment  
- Typeable user responses \- AI analyze

{% endtab %}

{% endtabs %}

## Sample Feature Matrix

{: .example }

Here's a simple example of a feature table with **Prototype**, **Core**, and **Stretch** features for a **ride-sharing app**:

| Feature Category | Prototype Features | Core Features (For complete product) | Stretch Features (Future Enhancements) |
| :---- | :---- | :---- | :---- |
| User Management | User registration & login | Profile verification & preferences | AI-based ride recommendations |
| Ride Booking | Basic ride request & acceptance | Ride scheduling & multi-stop trips | Subscription plans for frequent users |
| Payment | Cash or manual payment handling | In-app payments & fare estimation | Cryptocurrency payments |
| Navigation | Basic driver-passenger location sharing | Real-time traffic-based route optimization | Augmented reality (AR) navigation |
| Safety | Driver & passenger ratings | Emergency SOS button | AI-driven fraud detection & background checks |

For each of these features listed in the first column you should include more detailed specifics in separate paragraphs, like:

**User Management:** This will include how users connect to the system, how they are authenticated and any additional information that is attached to their user profile...

## Learning Targets and Challenge Goals

You must include what you plan to learn more on related to your project idea.

What do you need to know to be successful?  What technology or techniques are new to you that are likely to be difficult? What will you exercise and get better at because of this project?

Learning Targets:

- Learn how to code NPC and user interactions  
- Get comfortable with arrays and autoupdating them (for our task list)  
- Learn to switch screens smoothly


You can:

- List out Data Structures, Generics, and/or Interfaces that you intend to become more familiar with.  
- List out algorithms and/or public libraries List out GUI components or things like: multi-threading, security, web services, GitHub, sound, Fast Fourier Transforms, double buffering, image processing, fly out menus, Trees, networking.

{: .alert} Do not say vague things like, “To learn how to code the project”

## High Level Weekly Timeline

Create your task list from [this template](https://docs.google.com/spreadsheets/d/1F2ba3ekH2hg-wLWOCXHkyzi8Q8jizi4QHmhfRdichcU/edit?usp=sharing) (You may modify but you should keep this as the minimum required information for a task list).

You must include your phase 1 milestone task list in this document as markdown. (See below for an example; you can convert the google sheet to markdown using this tool here: [https://tabletomarkdown.com/convert-spreadsheet-to-markdown/](https://tabletomarkdown.com/convert-spreadsheet-to-markdown/))

{: .notice-title }

Task list details

1. Task \- A short description of a task. Keep tasks as simple as you can and avoid mixing tasks across multiple things. If you need to later you can cross out this task and split it into multiple items as you learn more (keeping your original estimate)  
2. Owner \- Who you plan to have work on this task.  
3. Status \- So you know if your partner is finished or actively working on this or hasn't started.  
4. Initial Estimate \- What's your best guess on time for this? If you have a task that is large that's fine but it's a good indication that you will need to split this up into multiple tasks on the week you decide to work on it. You won't change this after your initial design document is created.  
5. Expected Time \- Use your previous teams multiplier to get a \# hours for this.  
6. Week\#	\- Which week is this planned for? You can turn on week numbers in most calendars and use this for planning.  
7. Actual Time \- You'll record this as you finish tasks.  
8. Notes \- Any other stuff you want to put in to help coordinate on tasks.

### Sample Task List

| Task | Owner | Type | Status | Initial Estimate | Multiplier (Expected Time) | Week \# | Actual Time | Notes / Details / Description |
| :---- | :---- | :---- | :---- | :---- | :---- | :---- | :---- | :---- |
| Milestone 1 name |  | Milestone | In progress |  | 0.00 | 1 |  | Milestones are parents of your tasks (they are a summary of all of the weeks planned tasks) |
| Milestone 2 |  | Milestone | Not started |  | 0.00 | 2 | 0.00 |  |
|  |  |  |  |  | 0.00 |  |  |  |
|  |  |  |  |  | 0.00 |  |  |  |
|  |  |  |  |  | 0.00 |  |  |  |
|  |  |  |  |  | 0.00 |  |  |  |
|  |  |  |  |  | 0.00 |  |  |  |
|  |  |  |  |  | 0.00 |  |  |  |
|  |  |  |  |  | 0.00 |  |  |  |
|  |  |  |  |  | 0.00 |  |  |  |

{: .warning } Each week we have a little less than 4 hours of class time. Given other interruptions and activities, if you schedule more than 3 hours pp (per person) for a week you can expect you'll need to work outside of regular class hours on it.

## Approval

{: .alert } You must receive teacher approval to continue to Phase 2\. Your teacher will review all projects and send notice if you are not approved to continue to the next phase.  
