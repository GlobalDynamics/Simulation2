package com.engine.intelligence;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.engine.actions.Action;
import com.engine.actions.CompleteAction;
import com.engine.actions.MoveAction;
import com.engine.core.Tile;
import com.engine.types.TileDirection;
import com.engine.types.TileState;
import com.engine.util.Util;

public class AverageBot extends Bot {
	public int health = 100;
	public int stealthTurns = 0;
	public int teleportLength = 0;
	public int suicideArea = 1;
	
	//abilities
	public boolean hasStealth = false;
	public boolean hasTeleport = false;
	public boolean hasSuicide = true;
	
	public double probabilityOfAttack = .50;
	public double probabilityOfMove = .50;
	public double probabilityOfChase = .50;
	
	
	//status
	public boolean stealthActive = false;
	
	
	public Tile tile;
	
	public Action nextAction()
	{
		if(emptyFirstLevel())
		{
			if(tile.getEnemyList().size() > 0)
			{
				TileDirection direction=Util.getDirection(tile,getClosestBot());
				if(Util.checkIfEdge(tile, direction))
				{
					MoveAction moveAction = new MoveAction();
					moveAction.direction = direction;
					moveAction.tile = tile;
					//tile.moveTile(direction);
					return moveAction;
				}
				else
				{
					MoveAction moveAction = new MoveAction();
					moveAction.direction = Util.getOppositeDirection(direction);
					moveAction.tile = tile;
					//tile.moveTile(direction);
					return moveAction;
				}
			}
			else
			{
				CompleteAction win = new CompleteAction();
				return win;
			}
		}
		else
		{
			MoveAction moveAction = new MoveAction();
			moveAction.direction = TileDirection.UP;
			moveAction.tile = tile;
			//tile.moveTile(direction);
			return moveAction;
		}
		
	}
	
	public boolean emptyFirstLevel()
	{
		if(tile.getLeftState() != TileState.FILLED 
				&& tile.getRightState() != TileState.FILLED
				&& tile.getTopState() != TileState.FILLED
				&& tile.getBottomState() != TileState.FILLED)
		{
			return true;
		}
		return false;
	}
	
	public Tile getClosestBot()
	{
		Map<Double, Tile> distanceList = new HashMap<Double, Tile>();
		for(Tile t: tile.b.tileList)
		{
			if(!t.equals(tile))
			{
				double x1 = tile.positionX;
				double x2 = t.positionX;
				double y1 = tile.positionY;
				double y2 = t.positionY;
				distanceList.put(Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)), t);
			}
		}
		TreeMap<Double, Tile> sortedDistances = new TreeMap<Double, Tile>(distanceList);
		return sortedDistances.firstEntry().getValue();
	}
	
	public Tile getTile() {
		return tile;
	}
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	public void decrementHealth(int amount)
	{
		this.health = this.health - amount;
	}
	public void incrementHealth(int amount) {
		this.health = this.health + amount;
	}

}
