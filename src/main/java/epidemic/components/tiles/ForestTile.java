/*
 * Crystal Cleaveland
 * Epidemic Game
 * Tile.java - Representation of forest board tile
 * 28.Jun.2020
 */

package epidemic.components.tiles;

import epidemic.Game;

public class ForestTile extends Tile
{
    public ForestTile( int x, int y )
    {
        super( x, y );
        tileType = "Forest";
        infected = false;
    }

    public void interact()
    {
        // If cure has not been found, search for items
        if( !Game.getPlayer().hasCure() )
        {
            System.out.println( "Gathering..." );

            if( item.equals( "Strange Plant" ) )
            {
                System.out.println( "You discovered a strange plant!" );
                Game.getPlayer().addItemToInventory( "Strange Plant" );
            }
            else
            {
                System.out.println( "Nothing unusual was found." );
            }
        }
        // If cure has been found, give user hint for next actions
        else
        {
            System.out.println( "Why are you in the woods? Go help the sick townspeople!" );
        }
    }
}
