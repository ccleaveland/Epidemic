/*
 * Crystal Cleaveland
 * Epidemic Game
 * Tile.java - Representation of base board tile
 * 28.Jun.2020
 */

package epidemic.components.tiles;

import java.awt.*;

public abstract class Tile
{
    protected String tileType;
    protected String item = "";
    protected boolean infected;
    protected Point location = new Point();

    public Tile( int x, int y )
    {
        location.setLocation( x, y );
    }

    public abstract void interact();

    public String getTileType()
    {
        return tileType;
    }

    public void setItem( String item )
    {
        this.item = item;
    }

    public boolean isInfected()
    {
        return infected;
    }

    public void setInfectionStatus( boolean infected )
    {
        this.infected = infected;
    }

    public Point getTileLocation()
    {
        return location;
    }
}
