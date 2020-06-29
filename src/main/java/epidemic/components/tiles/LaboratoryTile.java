/*
 * Crystal Cleaveland
 * Epidemic Game
 * Tile.java - Representation of laboratory board tile
 * 28.Jun.2020
 */

package epidemic.components.tiles;

import epidemic.Game;

public class LaboratoryTile extends Tile
{
    public LaboratoryTile( int x, int y )
    {
        super( x, y );
        tileType = "Laboratory";
        infected = false;
    }

    public void interact()
    {
        if( !Game.getPlayer().hasCure() )
        {
            // If all three items are found, receive cure
            if( Game.getPlayer().getInventory().size() == 3 )
            {
                System.out.println( "Researching..." );
                System.out.println( "You've found the cure!" );
                System.out.println( "New town areas will not longer be infected." );
                System.out.println( "Distribute cure to all infected townspeople." );
                Game.getPlayer().setHasCure( true );
            }
            // If all items are not found, display error message
            else
            {
                System.out.println( "Player doesn't have all necessary components for cure." );
            }
        }
        // If cure has been found, give user hint for next actions
        else
        {
            System.out.println( "You already obtained the cure. Go help the sick townspeople!" );
        }
    }
}
