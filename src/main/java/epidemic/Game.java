/*
 * Crystal Cleaveland
 * Epidemic Game
 * Game.java - Game play logic
 * 28.Jun.2020
 */

package epidemic;

import epidemic.components.Board;
import epidemic.components.Player;
import epidemic.utils.InputValidationUtils;

public class Game
{
    private static final Board board = Board.getInstance();
    private static final Player player = Player.getInstance();

    private static final String MENU =
            "1 - Move\n" +
            "2 - Interact\n" +
            "3 - Display Inventory\n" +
            "4 - Exit\n" +
            "Selection: ";

    public static Board getBoard()
    {
        return board;
    }

    public static Player getPlayer()
    {
        return player;
    }

    public void play()
    {
        int mainMenuChoice;
        char direction;
        char[] directionOptions = { 'U','R','L','D' };

        do
        {
            // Display game board
            board.displayBoard();

            // Display game menu and retrieve input
            System.out.print( MENU );
            mainMenuChoice = InputValidationUtils.validateNum( 1, 4 );

            // If user chooses to move to another space
            if( mainMenuChoice == 1 )
            {
                // Retrieves and validates directional input from user
                System.out.print( "Direction ('U' - up, 'R' - right, 'L' - left, 'D' - down): " );
                direction = InputValidationUtils.validateAlphaChar( directionOptions );

                // Call move method and resolve infection consequences
                move( direction );
                resolveInfection();
            }
            // If user chooses to interact with environment
            else if( mainMenuChoice == 2 )
            {
                board.getCurrentTile().interact();
            }
            // If user chooses to display inventory
            else if( mainMenuChoice == 3 )
            {
                player.displayInventory();
            }
        }
        // Continue until user chooses to quit or game is over (from win or loss)
        while( mainMenuChoice != 4 && !board.isGameOver() && !player.isGameOver() );
    }

    private void move( char direction )
    {
        if( direction == 'U' )
        {
            board.moveUp();
        }
        else if( direction == 'R' )
        {
            board.moveRight();
        }
        else if( direction == 'L' )
        {
            board.moveLeft();
        }
        else
        {
            board.moveDown();
        }
    }

    private void resolveInfection()
    {
        // If current Tile is infected then player loses health
        if( board.getCurrentTile().isInfected() )
        {
            System.out.println( "Player is in an infected area and loses health." );
            player.decreaseHealth();
        }

        // Call spreadInfection method if no cure (chance of new infections on movement)
        if( !player.hasCure() && !player.isGameOver() )
        {
            board.spreadInfection();
        }
    }
}
