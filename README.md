# TicTacToe
This is an extended version of the classic Tic-tac-toe game. Two players play against each other and an AI on a board of configurable size.

## How to run
1. Clone or download the code from GitHub
2. Open a command prompt in the folder of the project (TicTacToe, not src)
3. Ensure there is a config.txt with the desired settings. Check format of config file below.
4. Compile with `javac src/game/*.java -d out/production/TicTacToe/`
5. Run with `java -classpath out/production/TicTacToe game.Main`
6. Play the game!

## Config file
The expected format is:
```
size:[NUMBER]
symbols:[SYMBOL],[SYMBOL],[SYMBOL]
ai:[DIFFICULTY]
```
* `[NUMBER]` has to be between 3 and 10 (inclusive)
* `[SYMBOL]` is a one character player symbol
* `[DIFFICULTY]` is either "easy" or "hard"

For example, a valid config file is:
```
size: 3
symbols: X, Y, Z
ai: hard
```

## Software design
I have tried to follow OOP and Clean Code principles. I have split responsibility to different classes.

`GameController` has the main logic of the game. It uses a `Board` containing a `Player` grid, which represents the playing field. `ConfigReader` reads a config file and parses the relevant attributes, which are accessed through getters. `BoardPrinter` and `PlayerManager` help `GameController` with specific aspects of the gameplay. `AI` is an abstract class extended by `RandomAI` and `StrategicAI`.

Below are explanations of several decision.
#### Player enum instead of int
Initially, I used a int grid where 0 meant empty and 1, 2, 3 were the three players. I realised quickly that this isn't a robust approach because this data structure can accept any other number. To avoid error states, a lot of validation had to be done.

Instead of this, I decided to create a `Player` enum. Now, `Board` contains a `Player` grid where empty spaces are represented by null values. This clarifies the intention of the grid and makes code less error prone(e.g. adding a move for a specific player).

#### AI abstract class
This was an easy decision. An AI with a different strategy can simply subclass `AI`, and `GameController` will accept the new class.

#### Keep track of players in PlayerManager
At first, keeping track of players was done in `GameController`. While implementing the random starting player feature, I decided it needed its own class. `PlayerManager` hides the randomisation of the player order and provides a simple interface to get the player whose turn it is.

#### Decided against Singleton for GameController
I have thought about making `GameController` follow the singleton pattern because there should only be one controller for a game. But I thought that this would make extending with a "Try again?" feature impossible.
More specifically, because `GameController` is not singleton, main can create several ones, so that you can play several rounds in the same run of the program.

Additionally, I didn't think that making `GameController` singleton would provide any benefits.

#### Position parsing as static function
This is a decision I am particularly proud of. At first, I wrote the input parsing of the position in `GameController` as a private function because only `GameController` was meant to use it. Then I tried testing it and realised that testing a private function from a different package is only possible through the Java reflection API. Then it became clear that this doesn't work when trying to test that the function throws the correct exceptions because reflection API catches these and throws its own exceptions. In any case, this was starting to be too complex for the simple testing of input parsing I wanted.

Therefore, I realised I can move this functionality to the `Position` class as a static function. This is similar to how the `Integer` class has the static `parseInt()` function. Obviously, this was much easier to test and a more elegant solution.

#### Invalid input handling
Everyone makes mistakes. That's okay and that's why we need to be given a second chance. :)

Based on this, player input is received within a while loop in `GameController`, so that the player is asked for input again if their choice was invalid for any reason (position filled, out of bounds, or wrong format).

---
Below are some weak points of my solution, that I think might need modification.
* AI and GameController need to receive the same Board.
* ConfigReader has implicit steps: call readFile() _before_ getting information.
* config file has some implicit rules (order of attributes). Using JSON would be clearer and a more sturdy approach.
