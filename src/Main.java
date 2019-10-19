import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

public class Main {


    public static final int RED_BOARD = 2000000;
    public static final int BLUE_BOARD = 1500000;
    static HashMap<Integer, Element> myBoard;

    public static void settingTheBoard(int size) {
        // Setting the board
        myBoard = new HashMap<>();

        for(int i = 0; i < size; i++) {
            myBoard.put(i, new Space());
        }
    }

    public static void minningTheBoard(int numberMines) {
        Random random = new Random();

        for(int i = 0; i<myBoard.size(); i++){
            myBoard.put(i, new Mine());
        }
        int noOfSpaces = myBoard.size() - numberMines;
        while (noOfSpaces > 0) {
            Integer trial = new Integer(random.nextInt(myBoard.size()));

            if (myBoard.get(trial) instanceof Space) {
                myBoard.put(trial, new Mine());
                numberMines--;
            }
        }
    }

    public static void main(String[] args) {

        long startingTime = System.currentTimeMillis();
        settingTheBoard(RED_BOARD);
        minningTheBoard(1500000); //
        long elapsedTime = (System.currentTimeMillis() - startingTime);
        System.out.println("1,500,000 bombs planted in " + elapsedTime + " milliseconds");

        startingTime = System.currentTimeMillis();
        settingTheBoard(BLUE_BOARD);
        minningTheBoard(1499999); //
        elapsedTime = (System.currentTimeMillis() - startingTime);
        System.out.println("1,499,999 bombs planted in " + elapsedTime + " milliseconds");

        System.out.println("I really need help with Software Development... if you read this... send help pls");
    }
}
