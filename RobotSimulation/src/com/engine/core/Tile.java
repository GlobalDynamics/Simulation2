package com.engine.core;

import java.util.ArrayList;
import java.util.List;

import com.engine.actions.Action;
import com.engine.intelligence.Bot;
import com.engine.types.TileDirection;
import com.engine.types.TileState;
import com.engine.types.TileType;
import com.engine.util.Util;

public class Tile {
	public Board b;
	public TileType tileType;
	public Bot bot;
	public int positionX;
	public int positionY;
	
	public Tile(TileType tileType, int positionX, int positionY)
	{
		this.positionX = positionX;
		this.positionY = positionY;
		this.tileType = tileType;
	}
	public Tile(TileType tileType, int positionX, int positionY, Bot bot)
	{
		this.positionX = positionX;
		this.positionY = positionY;
		this.tileType = tileType;
		this.bot = bot;
	}
	
	public List<Tile> getListFriendlyList()
	{
		List<Tile> friendList = new ArrayList<Tile>();
		for(Tile t : b.tileList)
		{
			if(t.tileType == this.tileType)
			{
				friendList.add(t);
			}
		}
		return friendList;
	}
	
	public List<Tile> getEnemyList()
	{
		List<Tile> enemyList = new ArrayList<Tile>();
		for(Tile t : b.tileList)
		{
			if(t.tileType != this.tileType)
			{
				enemyList.add(t);
			}
		}
		return enemyList;
	}
	
	public Action getNextAction()
	{
		return bot.nextAction();
	}
	
	public TileState getLeftState()
	{
		int leftTile = this.positionX -1;
		if(leftTile < 0)
		{
			return TileState.X_START;
		}
		else if(!b.isTileEmpty(leftTile, this.positionY))
		{
			return TileState.FILLED;
		}
		else if(b.isTileEmpty(leftTile, this.positionY))
		{
			return TileState.EMPTY;
		}
		else
		{
			//Best not to go at all
			return TileState.FILLED;
		}
	}
	public TileState getRightState()
	{
		int boardEndPointX = b.boardWidth;
		int boardEndPointY = b.boardLength;
		int rightTile = this.positionX +1;
		if(rightTile > boardEndPointX)
		{
			return TileState.X_END;
		}
		else if(!b.isTileEmpty(rightTile, this.positionY))
		{
			return TileState.FILLED;
		}
		else if(b.isTileEmpty(rightTile, this.positionY))
		{
			return TileState.EMPTY;
		}
		else
		{
			//Best not to go at all
			return TileState.FILLED;
		}
	}
	public TileState getTopState()
	{
		int boardEndPointX = b.boardWidth;
		int boardEndPointY = b.boardLength;
		int topTile = this.positionY -1;
		if(topTile > boardEndPointX)
		{
			return TileState.Y_END;
		}
		else if(!b.isTileEmpty(topTile, this.positionY))
		{
			return TileState.FILLED;
		}
		else if(b.isTileEmpty(topTile, this.positionY))
		{
			return TileState.EMPTY;
		}
		else
		{
			//Best not to go at all
			return TileState.FILLED;
		}
	}
	public TileState getBottomState()
	{
		int bottomTile = this.positionY +1;
		if(bottomTile < 0)
		{
			return TileState.Y_START;
		}
		else if(!b.isTileEmpty(bottomTile, this.positionY))
		{
			return TileState.FILLED;
		}
		else if(b.isTileEmpty(bottomTile, this.positionY))
		{
			return TileState.EMPTY;
		}
		else
		{
			//Best not to go at all
			return TileState.FILLED;
		}
	}
	
	public void moveTile(TileDirection direction)
	{
		if(direction == TileDirection.UP)
		{
			moveUp();
		}
		else if(direction == TileDirection.DOWN)
		{
			moveDown();
		}
		else if(direction == TileDirection.LEFT)
		{
			moveLeft();
		}
		else if(direction == TileDirection.RIGHT)
		{
			moveRight();
		}
	}
	
	public Tile getTopTile()
	{
		if(getTopState() == TileState.FILLED)
		{
			return b.getTileByCoordinates(this.positionX, this.positionY-1);
		}
		else
		{
			return null;
		}
	}
	
	public Tile getBottomTile()
	{
		if(getBottomState() == TileState.FILLED)
		{
			return b.getTileByCoordinates(this.positionX, this.positionY+1);
		}
		else
		{
			return null;
		}
	}
	
	public Tile getRightTile()
	{
		if(getRightState() == TileState.FILLED)
		{
			return b.getTileByCoordinates(this.positionX+1, this.positionY);
		}
		else
		{
			return null;
		}
	}
	
	public Tile getLeftTile()
	{
		if(getLeftState() == TileState.FILLED)
		{
			return b.getTileByCoordinates(this.positionX-1, this.positionY);
		}
		else
		{
			return null;
		}
	}
	
	public void moveUp()
	{
		this.positionY = this.positionY -1;
	}
	
	public void moveDown()
	{
		this.positionY = this.positionY +1;
	}
	public void moveRight()
	{
		this.positionX = this.positionX +1;
	}
	public void moveLeft()
	{
		this.positionX = this.positionX -1;
	}
	
	public void attackTile(Tile tile)
	{
		int damage = Util.randInt(5, 15);
		tile.bot.decrementHealth(damage);
	}
	
}
