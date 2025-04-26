# A Wordle clone made in Java with a leaderboard, final project for CSC335

## The design of our code:

The project is a Wordle game that was heavily inspired by the design of
Small Assignment 6, which had a GUI based on Model-View-Controller-Observer
design model and Observer interface. The user interface is handled by the
frontend (the view classes). The GUI, itself, was made with Swing.
The backend reads and writes to a .csv file for storing user data. A
leaderboard was also added for additional complexity, which displays the
top 10 users with the most amount of victories, regardless of whether or
not they had scored in separate session. Additionally, the project implements
a login component that uses salting and hashing for extra security. We also
added functionality for allowing users to not only logout, but switch between
multiple screens. Escaping references are avoided when possible and, if 
absolutely necessary, handled with appropriate copies (with depth extending
to as far as needed to prevent unwanted access to mutable objects). We chose
enums for the colors to avoid the primitive obsession. AI-generated code was
used for some of the visual aspects of the GUI.


## How to run our code:
- Clone this repository
- Download IntelliJ Community Edition
- Open the repository inside IntelliJ
- Add JUnit 4 (junit:junit:4.13.1) to classpath
- Add JUnit 5 (org.junit.jupiter:junit-jupiter:5.8.1) to classpath
- Run Main.java
