/*
 * Crystal Cleaveland
 * Epidemic Game
 * Main.java - Display information and set up game
 * 28.Jun.2020
 */

package epidemic;

import epidemic.components.Board;
import epidemic.utils.InputValidationUtils;

public class Main
{
    private static final String INTRODUCTION =
            "An outbreak is occurring in your village!\n" +
            "Your job is to gather the materials needed to find a cure and deliver that cure to the infected townspeople.\n" +
            " - First, you need to explore the town and forest to find the THREE necessary items.\n" +
            " - Second, you need to deliver those items to the laboratory to discover a cure.\n" +
            " - Third, you must go to infected areas of the town and use the cure until the outbreak is eradicated.\n" +
            "Win condition:\n" +
            "\tSuccessfully cure all disease from board.\n" +
            "Lose conditions:\n" +
            "\t1) All town areas infected\n" +
            "\t2) Player loses all health\n" +
            "One town area begins infected and other areas are infected as game progresses.\n" +
            "Player loses health when moving to infected area and regains health at the hospital.\n";

    private static final String BOARD_DETAILS =
            "'_' is town, 'T' is forest, 'L' is the laboratory, 'H' is the hospital, '@' is the player, and '*' is disease.\n" +
            "Player starts at the hospital.";

    public static void main( String[] args )
    {
        // Display game intro
        System.out.println( INTRODUCTION );

        // Retrieve board size
        System.out.print( "How many tiles across would you like the board (5-10)? " );
        int boardSize = InputValidationUtils.validateNum( 5, 10 );

        // Create game board
        Board board = Board.getInstance();
        board.setupBoard( boardSize );

        // Display board legend
        System.out.println( "\n" + BOARD_DETAILS );
        System.out.println( "Starting map:" );

        // Play game
        Game game = new Game();
        game.play();

        System.out.println( "Goodbye!" );
    }
}
