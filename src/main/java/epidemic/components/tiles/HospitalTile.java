/*
 * Crystal Cleaveland
 * Epidemic Game
 * Tile.java - Representation of hospital board tile
 * 28.Jun.2020
 */

package epidemic.components.tiles;

import epidemic.Game;

public class HospitalTile extends Tile
{
    public HospitalTile( int x, int y )
    {
        super( x, y );
        tileType = "Hospital";
        infected = false;
    }

    public void interact()
    {
        // Heal player to full health
        System.out.println( "Healing..." );
        System.out.println( "Health has been restored!" );
        Game.getPlayer().restoreHealth();
    }
}
