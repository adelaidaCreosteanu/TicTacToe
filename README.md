# TicTacToe
This is an extended version of the classic Tic-tac-toe game. Two players play against each other and an AI on a board of configurable size.

## How to run
1. Clone or download the code from GitHub
2. Open a command prompt in the folder of the project (TicTacToe, not src)
3. Ensure there is a config.txt with the desired settings
4. Compile with `javac src/game/*.java -d out/production/TicTacToe/`
5. Run with `java -classpath out/production/TicTacToe game.Main`
6. Play the game!

## Software design
I have tried to follow OOP and Clean Code principles. I have split responsibility to different classes.

Design decisions:
* Player enum instead of int
* AI abstract class
* keep track of players in PlayerManager
* decided against Singleton for GameController
* position parsing as static function

Weak points:
* AI and GameController need to receive the same Board.
* have to follow implicit steps with ConfigReader: call readFile() _before_ getting information.
* config file has some implicit rules (order of attributes). Using JSON would be clearer and a more sturdy approach.
