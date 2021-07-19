Original App Design Project - README
===

# Dog Tracker

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
This is an app that organizes who's turn it is to complete certain tasks when it comes to taking care of a dog(s)

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Daily tracking and lifestyle?
- **Mobile:** Best for moblie because of the ability to notify user at any moment
- **Story:** Allows family to be reminded who's turn it is to take care of the small parts of owning a dog. 
- **Market:** This is can be used by anyone that owns a dog. 
- **Habit:** Use throughout the day to take care of dog
- **Scope:** Could be implemented to include other animals, not just dogs. Could link a family to a single organizer across multiple devices. Could pull information online to remind users about small things like how often to walk the dog etc.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Different activities: Start up screen for selecting the type of dog (for searching information), could show a calandar/daily timeline screen to show how things are being organized.
* pet profiles?
* Need to have a database to keep track of the schedule on who's turn it is if there are multiple people in the house hold.
* Log in pulls up previous information.
* Probably going to use google to look up information on certain dogs.
* Use gesture to maually alter schedule?
* use animation for transitions

**Optional Nice-to-have Stories**

* Have a page for misc. facts about dog pulled from onlie
* Have multiple users connect to a single planner/"household"

### 2. Screen Archetypes

* Shows list of things to do on startup screen if already logged in
   * pull information from database to display
   * animation to go from week view to month view?
   * option to add another dog
* Log in/Sign up screen that takes you to set up page
   * Setup page pulls up a series of views that lead you through setting up a household
* Main menu
    * Shows calendar View of Task
    * Shows Picture of dogs as cards at bottom
* Dog profile
    * Show information about the dog
        * Random information pulled from API
        * Age, weight, name etc.
    * option to remove pet
* owner profile
    * Shows the tasks per week the particular owner has, also shows a list of tasks the owner needs to do
    * option to remove owner

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Add owner
* Add dog
* log out
* link to owner profile

**Flow Navigation** (Screen to Screen)

* Log in screen
    * Main menu
* Main menu
    * Dog details
    * owner details
    * Add pet screen
    * Add owner screen
    * Create custom task screen
* Create account
    * New household screen to add owners
* New household screen
    * Add pet screen
* Add pet screen
    * link to itself if adding another dog
    * Main menu
* All other link back to main menu through Dashboard

## Wireframes
<img src="Username.pdf" width=600>

## Schema 
### Models
#### Post

   | Property      | Type         | Description |
   | ------------- | ------------ | ------------|
   | objectId      | String       | unique id for the user post (default field) |
   | Owners        | Array[Owner] | List of owners in the household.            |
   | Dogs          | Array[Dogs]  | List of dogs in the household               |
   | Tasks         | Array[Task]  | List of tasks                               |
   |               |              |                                             |
#### Task Object
   | Property      | Type         | Description |
   | ------------- | ------------ | ----------- |
   | time          | DateTime     | Time to do the tast
   | Color         | String       | Color to be displayd on calendar
   
#### Dog Object
   |    Property   | Type         | Description |
   | ------------- | ------------ | ----------- |
   | name          | String       | name of the dog
   | breed         | String       | breed of the dog
   | age           | int          | age of the dog
   
#### Owner Object
   | Property      | Type         | Description |
   | ------------- | ------------ | ----------- |
   | name          | String       | owners name
   | assignedTasks | Array[Task]  | list of tasks the owner has coming
   
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
