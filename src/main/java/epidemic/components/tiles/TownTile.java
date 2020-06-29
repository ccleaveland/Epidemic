/*
 * Crystal Cleaveland
 * Epidemic Game
 * Tile.java - Representation of town board tile
 * 28.Jun.2020
 */

package epidemic.components.tiles;

import epidemic.Game;

public class TownTile extends Tile
{
    public TownTile( int x, int y )
    {
        super( x, y );
        tileType = "Town";
        infected = false;
    }

    public void interact()
    {
        // If cure has not been found, search for items
        if( !Game.getPlayer().hasCure() )
        {
            System.out.println( "Searching..." );

            if( item.equals( "Vial of Infected Blood" ) )
            {
                System.out.println( "You obtained a vial of infected blood!" );
                Game.getPlayer().addItemToInventory( "Vial of Infected Blood" );
            }
            else if( item.equals( "Dr. Jenner" ) )
            {
                System.out.println( "You found Dr. Jenner!" );
                Game.getPlayer().addItemToInventory( "Dr. Jenner" );
            }
            else
            {
                System.out.println( "Nothing of interest was discovered." );
            }
        }
        // If cure has been found, attempt to use cure on infection
        else
        {
            if( isInfected() )
            {
                System.out.println( "Townspeople cured!" );
                setInfectionStatus( false );
                Game.getBoard().decreaseNumInfectedTowns();
            }
            else
            {
                System.out.println( "No outbreak present in this area." );
            }
        }
    }
}
