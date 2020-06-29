/*
 * Crystal Cleaveland
 * Epidemic Game
 * Board.java - Representation of game board
 * 28.Jun.2020
 */

package epidemic.components;

import epidemic.components.tiles.*;

import java.awt.*;
import java.util.Random;

public class Board
{
    private static Board instance;

    private Tile[][] board;
    private int boardSize;
    private int numTownTiles;
    private int numInfectedTiles;
    private boolean gameOver;
    private Tile current;
    private final Random random = new Random();

    private Board()
    {
        gameOver = false;
    }

    public static Board getInstance()
    {
        if( instance == null )
        {
            instance = new Board();
        }

        return instance;
    }

    public void setupBoard( int boardSize )
    {
        this.boardSize = boardSize;
        board = new Tile[ boardSize ][ boardSize ];

        // Create tiles on the board
        createBoardTiles();

        // Place items on the board
        setBoardItems();

        // Infect board
        determineInfectionLocation();
    }

    public void displayBoard()
    {
        System.out.println();
        // Go through every tile on board
        for( int col = 0; col < boardSize; col++ )
        {
            for( int row = 0; row < boardSize; row++ )
            {
                // If tile is occupied, print "@"
                if( board[col][row].equals( current ) )
                {
                    System.out.print( " @" );
                }
                // If tile is infected but not occupied, print "*"
                else if( board[col][row].isInfected() )
                {
                    System.out.print( " *" );
                }
                // If tile is not infected or occupied and a TownTile, print "_"
                else if( board[col][row].getTileType().equals( "Town" ) )
                {
                    System.out.print( " _" );
                }
                // If tile is not occupied and a ForestTile, print "T"
                else if( board[col][row].getTileType().equals( "Forest" ) )
                {
                    System.out.print( " T" );
                }
                // If tile is not occupied and a HospitalTile, print "H"
                else if( board[col][row].getTileType().equals( "Hospital" ) )
                {
                    System.out.print( " H" );
                }
                // If tile is not occupied and a LaboratoryTile, print "L"
                else if( board[col][row].getTileType().equals( "Laboratory" ) )
                {
                    System.out.print( " L" );
                }

                // Print new line for next row
                if( row == boardSize - 1 )
                {
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

    public void moveUp()
    {
        Point currentLocation = current.getTileLocation();

        // Display error message if no Tile exists above current Tile
        if( currentLocation.y == 0 )
        {
            System.out.println( "Cannot move further up." );
        }
        // Update player location
        else
        {
            current = board[ currentLocation.y - 1 ][ currentLocation.x ];
        }
    }

    public void moveDown()
    {
        Point currentLocation = current.getTileLocation();

        // Display error message if no Tile exists above below current Tile
        if( currentLocation.y == boardSize - 1 )
        {
            System.out.println( "Cannot move further down." );
        }
        // Update player location
        else
        {
            current = board[ currentLocation.y + 1 ][ currentLocation.x ];
        }
    }

    public void moveLeft()
    {
        Point currentLocation = current.getTileLocation();

        // Display error message if no Tile exists to left of current Tile
        if( currentLocation.x == 0 )
        {
            System.out.println( "Cannot move further left." );
        }
        // Update player location
        else
        {
            current = board[ currentLocation.y ][ currentLocation.x - 1 ];
        }
    }

    public void moveRight()
    {
        Point currentLocation = current.getTileLocation();

        // Display error message if no Tile exists to right of current Tile
        if( currentLocation.x == boardSize - 1 )
        {
            System.out.println( "Cannot move further right." );
        }
        // Update player location
        else
        {
            current = board[ currentLocation.y ][ currentLocation.x + 1 ];
        }
    }

    public Tile getCurrentTile()
    {
        return current;
    }

    public void decreaseNumInfectedTowns()
    {
        numInfectedTiles--;

        // If no more infected towns remain then player wins
        if( numInfectedTiles == 0)
        {
            System.out.println( "All townspeople have been cured - YOU WIN!" );
            gameOver = true;
        }
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public void spreadInfection()
    {
        Point infectionLocation = new Point();

        // Generate a random number between 1 and 100
        // If number is less than or equal to 25, infect a city
        if( random.nextInt( 100 ) + 1 <= 25 )
        {
            // Randomly generate coordinates on the board and regenerate until the corresponding Tile
            // is a TownTile that isn't already infected
            do
            {
                infectionLocation.setLocation( random.nextInt( boardSize ), random.nextInt( boardSize ) );
            }
            while( !board[ infectionLocation.y ][ infectionLocation.x ].getTileType().equals( "Town" ) ||
                    board[ infectionLocation.y ][ infectionLocation.x ].isInfected() );

            // Infect Tile, display location of newly infected Tile, and increase numInfectedTiles
            board[ infectionLocation.y ][ infectionLocation.x ].setInfectionStatus( true );
            System.out.println( "Town area at " + ( infectionLocation.x + 1 ) + ", "
                    + ( infectionLocation.y + 1 ) + " became infected." );
            numInfectedTiles++;

            // If all of the TownTiles on the Board are infected then the game is over
            if( numTownTiles == numInfectedTiles )
            {
                gameOver = true;
                System.out.println( "All town areas infected - GAME OVER!" );
            }
        }
    }

    private void createBoardTiles()
    {
        Point hospitalLocation = new Point();
        Point laboratoryLocation = new Point();

        // Determine location for hospital
        hospitalLocation.setLocation( random.nextInt( boardSize ), random.nextInt( boardSize ) );

        // Determine location for laboratory - make sure it isn't the same as hospital
        do
        {
            laboratoryLocation.setLocation( random.nextInt( boardSize ), random.nextInt( boardSize ) );
        }
        while( laboratoryLocation.equals( hospitalLocation ) );

        // Create tiles for every location on board
        for( int col = 0; col < boardSize; col++ )
        {
            for( int row = 0; row < boardSize; row++ )
            {
                // If coordinates match hospital coordinates, create hospital tile
                if( row == hospitalLocation.x && col == hospitalLocation.y )
                {
                    board[col][row] = new HospitalTile( row, col );
                }
                // If coordinates match laboratory coordinates, create laboratory tile
                else if( row == laboratoryLocation.x && col == laboratoryLocation.y )
                {
                    board[col][row] = new LaboratoryTile( row, col );
                }
                // Create either a town (60%) or forest (40%) tile
                else
                {
                    // Create a random number between 1 and 100
                    // If number is less than or equal to 60, create town tile
                    if( random.nextInt( 100 ) + 1 <= 60 )
                    {
                        board[col][row] = new TownTile( row, col );
                        numTownTiles++;
                    }
                    // If number is greater than 60, create forest tile
                    else
                    {
                        board[col][row] = new ForestTile( row, col );
                    }
                }
            }
        }

        // Set player starting location at the HospitalTile
        current = board[hospitalLocation.y][hospitalLocation.x];
    }

    private void setBoardItems()
    {
        Point item1Location = new Point();
        Point item2Location = new Point();
        Point item3Location = new Point();

        // Place item - Strange plant
        // Must be placed on a ForestTile
        do
        {
            item1Location.setLocation( random.nextInt( boardSize ), random.nextInt( boardSize ) );
        }
        while( !board[ item1Location.y ][ item1Location.x ].getTileType().equals( "Forest" ) );

        board[ item1Location.y ][ item1Location.x ].setItem( "Strange Plant" );

        // Place item - Vial of infected blood
        // Must be placed on a TownTile
        do
        {
            item2Location.setLocation( random.nextInt( boardSize ), random.nextInt( boardSize ) );
        }
        while( !board[ item2Location.y ][ item2Location.x ].getTileType().equals( "Town" ) );

        board[ item2Location.y ][ item2Location.x ].setItem( "Vial of Infected Blood" );

        // Place item - Dr. Jenner
        // Must be placed on a TownTile that doesn't contain vial of infected blood
        do
        {
            item3Location.setLocation( random.nextInt( boardSize ), random.nextInt( boardSize ) );
        }
        while( !board[ item3Location.y ][ item3Location.x ].getTileType().equals( "Town" ) ||
                item2Location.equals( item3Location ) );

        board[ item3Location.y ][ item3Location.x ].setItem( "Dr. Jenner" );
    }

    private void determineInfectionLocation()
    {
        Point infectionLocation = new Point();

        // Set starting infection location
        // Must be a TownTile
        do
        {
            infectionLocation.setLocation( random.nextInt( boardSize ), random.nextInt( boardSize ) );
        }
        while( !board[ infectionLocation.y ][ infectionLocation.x ].getTileType().equals( "Town" ) );

        board[ infectionLocation.y ][ infectionLocation.x ].setInfectionStatus( true );
        numInfectedTiles = 1;
    }
}
