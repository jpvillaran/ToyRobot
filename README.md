# ToyRobot

This program was developed using Java 8.

The main objective of this is to emulate the movements of a toy robot given a series of commands.  The main entry point for running this is the Program class that resides on the default package of the application.  

##Usage:
###1. Via inputted commands
Execute the program on any console by going to the bin directory after compiling the classes.
Do not enter any arguments when executing the Program class to use the Inputted Commands mode.
e.g. 
``` 
java Program
```

###2. Via a text file
Execute the program on any console by going to the bin directory after compiling the classes.
The first argument of the Program class should be a path pointing to the file containing the series of commands.
e.g.
```
java Program /path/to/file
```

##Commands
The Toy Robot could understand 5 basic commands:

###PLACE
Places a the Toy Robot on a 5x5 table (adjustable size in the program), using a location specified by the user.
e.g.
```
PLACE 1,1,EAST
```

The location is in the format X,Y,Direction, where the direction value may be one of the following (NORTH, EAST, SOUTH, WEST)

###LEFT
Rotates the robot counter-clockwise so that if it's current direction is NORTH, it will turn WEST upon executing this command.

###RIGHT
Rotates the robot clockwise so that if it's current direction is NORTH, it will turn EAST upon executing this command.

###MOVE
Moves the robot 1 unit to the direction it's currently facing.  If the movement will cause the robot to fall on the table, an error will be displayed and the robot will retain its position and direction.

###REPORT
Displays the current location of the robot on the table.

An example of a value displayed is:
```
The current location is (X = 1, Y = 2, Direction = NORTH)
```

##Constraints
The program will accept any command given, but in order to perform a series of commands that the robot will obey, it first has to be placed anywhere on the table.  Therefore, the first command that robot will understand before moving and rotating will be:
```
PLACE [X],[Y],[DIRECTION]
```
