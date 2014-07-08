package com.engine.util;

import com.engine.core.Tile;
import com.engine.types.TileDirection;
import com.engine.types.TileState;

public class Util {
	
	
	public static TileDirection getOppositeDirection(TileDirection direction)
	{
		if(direction == TileDirection.UP)
		{
			return TileDirection.DOWN;
		}
		else if(direction == TileDirection.DOWN)
		{
			return TileDirection.UP;
		}
		else if(direction == TileDirection.LEFT)
		{
			return TileDirection.RIGHT;
		}
		else if(direction == TileDirection.RIGHT)
		{
			return TileDirection.LEFT;
		}
		return TileDirection.NONE;
	}
	
    public static double getAngle(Tile p1, Tile p2)
    {
        double xDiff = p2.positionX - p1.positionX;
        double yDiff =  p2.positionY -p1.positionY;
        return 180 - Math.toDegrees(Math.atan2(yDiff, xDiff));
    }
    
    public static boolean checkIfEdge(Tile tile, TileDirection direction)
    {
    	if(direction == TileDirection.UP && tile.getTopState() == TileState.EMPTY)
    	{
    		return false;
    	}
    	else if(direction == TileDirection.DOWN && tile.getBottomState() == TileState.EMPTY)
    	{
    		return false;
    	}
    	else if(direction == TileDirection.LEFT && tile.getLeftState() == TileState.EMPTY)
    	{
    		return false;
    	}
    	else if(direction == TileDirection.RIGHT && tile.getRightState() == TileState.EMPTY)
    	{
    		return false;
    	}
		return true;
    }
    
    public static TileDirection getDirection(Tile p1, Tile p2)
    {
    	double angle = getAngle(p1, p2);
    	angle = normalizeAngle(angle);
    	if(angle >= 0 && angle <90)
    	{
    		return TileDirection.LEFT;
    	}
    	else if(angle >= 90 && angle <180)
    	{
    		return TileDirection.DOWN;
    	}
    	else if(angle >= 180 && angle <270)
    	{
    		return TileDirection.RIGHT;
    	}
    	else if(angle >= 270 && angle <360)
    	{
    		return TileDirection.UP;
    	}
    	else if(angle == 360)
    	{
    		return TileDirection.LEFT;
    	}
		return TileDirection.NONE;
    }
    
    private static double normalizeAngle(double angle)
    {
    	if(isPositive(angle))
    	{
    		return angle;
    	}
    	else
    	{
    		return 360 + angle;
    	}
    }
    public static boolean isPositive(double f) {
        if (f != f) throw new IllegalArgumentException("NaN");
        if (f == 0) return false;
        f *= Double.POSITIVE_INFINITY;
        if (f == Double.POSITIVE_INFINITY) return true;
        if (f == Double.NEGATIVE_INFINITY) return false;

        //this should never be reached, but I've been wrong before...
        throw new IllegalArgumentException("Unfathomed double");
    }
}
