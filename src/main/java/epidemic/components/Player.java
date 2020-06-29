/*
 * Crystal Cleaveland
 * Epidemic Game
 * Player.java - Representation of game player
 * 28.Jun.2020
 */

package epidemic.components;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private static Player instance;

    private int health;
    private final ArrayList<String> inventory = new ArrayList<>();
    private boolean gameOver;
    private boolean hasCure;

    private Player()
    {
        health = 5;
        gameOver = false;
        hasCure = false;
    }

    public static Player getInstance()
    {
        if( instance == null )
        {
            instance = new Player();
        }

        return instance;
    }

    public void decreaseHealth()
    {
        health -= 1;

        // If player is out of health then game is over
        if( health == 0 )
        {
            gameOver = true;
            System.out.println( "Player has no health remaining - GAME OVER!" );
        }
        // Display updated health amount if game is not over
        else
        {
            System.out.println( "Player has " + health + " health remaining." );
        }
    }

    public void restoreHealth()
    {
        health = 5;
    }

    public boolean hasCure()
    {
        return hasCure;
    }

    public void setHasCure( boolean hasCure )
    {
        this.hasCure = hasCure;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public List<String> getInventory()
    {
        return inventory;
    }

    public void addItemToInventory( String item )
    {
        inventory.add( item );
    }

    public void displayInventory()
    {
        System.out.println( "Inventory: " );

        if( inventory.isEmpty() )
        {
            System.out.println( "\tempty" );
        }
        else
        {
            for( String item : inventory )
            {
                System.out.println( "\t" + item );
            }
        }
    }
}
