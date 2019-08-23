public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe 2.0!\n");
        System.out.println("How to play:");
        System.out.println("You make a move by deciding on the coordinates of your next mark.");
        System.out.println("Please put the row number first, then a comma, then the column number.");
        System.out.println("Numbering starts from 1.\n");
        GameController game = new GameController();
        game.play();
    }
}
