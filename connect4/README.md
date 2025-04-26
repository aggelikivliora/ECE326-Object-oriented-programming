GAME OBJECTIVE:
The player competes against an AI that plans 1, 3, or 5 moves ahead, depending on the selected difficulty (easy, medium, hard).
On each turn, the player selects a column, and their token falls into the lowest available slot.
The objective is to connect four tokens in a row — vertically, horizontally, or diagonally — before the opponent.

HISTORY:
The first time you run the program, a file named "Connect4" will be created in your Home directory to store your game history. Later, you can revisit previous games by simply double-clicking on any saved entry.

COMPILE:
javac -cp ".:../libs/json-20250107.jar" -d bin Connect4.java  minMaxOptimalTree.java  Node.java GamePlay.java  myButton.java  PreviousGames.java

RUN:
java -cp ".:../libs/json-20250107.jar:bin" connect4.src.Connect4