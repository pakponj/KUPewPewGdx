Welcome to KUPewPew, our group final project.
==========

Members
----
* 5710547182 Thanawit Gerdprasert
* 5710546381 Pakpon Jetapai
* 5710546372 Pipatpol Tanavongchinda


Brief
---
Our project is space shooting game created by Library called "GDX" this library allow us to render easily and take less resource than using Native Android.

How to play :
===
- Touch the Game Start Button.
- Use your finger to control your movement.
- Shoot the enemy as much as you can and you can take the bomb to clear the screen.
- If you die to can press the Retry button to retry again.

Our Pattern use 8 patterns in total.
====
Singleton
---
**Location:** Game class and Player class.  
**Context:** Our class Game and Player are Singleton so that it is easier to called the function inside its.

Strategy
--
**Location:** Strategy Package.  
**Context:** Our Enemy class is move in different type, so we use Strategy to separate the move set, it can move
in the straight line, approaching and Spiral.

Factory
---
**Location:** Package called Factory.  
**Context:** We want our enemy to create randomly with different type so we use Factory Class to generate our enemies for us.

State
---
**Location:** ScreenManager class.  
**Context:** State is break down into different State, GameStart, GameRunning, GameOver so we use ScreenManager to control the flow of the State.

Observer
---
**Location:** Game class as Observer, Score class as Observable.  
**Context:** We want the Score class to update whenever the bullet is hit the enemy so we use this class in order to.

FlyWeight
---
**Location:** Game Class.  
**Context:** GDX provide us the method draw() that will draw the texture but if the texture is created every time the code is render then it will take a lot of resource for the game so we use this pattern to improve our performance.

ObjectPool
---
**Location:** EnemyPool class / BulletPool class.  
**Context:** Our program has infinite enemy and infinite bullet if we gonna created new one all the time it will take too much resource for our game so we define our pool class to keep the old object and reuse it for further use.

Mediator
---
**Location:** Game class.  
**Context:** Our class take all the component and run in the Mediator class that linked all the logic and all the view together.

The Principles that we use in this project
====

Open Close Principle 
- Most of our class has the core class as interfaces, and we use the subclass to extends or implement so that the core class will not be change, abd all other class are private so that you can't access from other places.

Don't Repeat Yourself and Single Responsibility Principle
- The code has only purpose in each Class, we use it as its own and any other class can't substitute that class.

Leskov Substitution Principle
 - Any other class can act as their own mother class, you can use the subclass as the super class with no error

GRASP
 - GRASP has really repetitive principle with chaining behavior together. The principle that we use are 
*Polymorphism - Any other subclass will either be implements or extends from mother class
*Controller - Our Game class is control by different model
*Low Coupling - We use mediator class so the each class has its own purpose and linked my mediate class
*Creator - The factory pattern cover this principle for us
*Protected Variation - Our code variable are almost all private except the singleton part
*Pure Fabrication - Our code has its own only purpose for each class
*Indirection - Game class as the mediator for the game
