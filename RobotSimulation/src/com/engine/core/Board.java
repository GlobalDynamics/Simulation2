package com.engine.core;

import java.util.ArrayList;
import java.util.List;

import com.engine.actions.Action;
import com.engine.intelligence.AverageBot;
import com.engine.intelligence.Bot;
import com.engine.types.TileType;

public class Board {
	//public Tile[][] boardArray;
	public int boardLength = 20;
	public int boardWidth = 20;
	public List<Tile> tileList = new ArrayList<Tile>();
//	public List<Tile> blueList = new ArrayList<Tile>();
//	public List<Tile> redList = new ArrayList<Tile>();
//	public List<Tile> yellowList = new ArrayList<Tile>();
	
	public TileType nextTurn = TileType.BLUE;
	
	
	public Board(Tile[][] boardArray, int boardLength, int boardWidth)
	{
		this.boardLength = boardLength;
		this.boardWidth = boardWidth;
		
//		 int [] [] test = {{2,0,1,0,0}, 
//             {1,0,0,0,0},
//             {0,0,0,0,0},  //1 is unit
//             {0,0,0,0,0},
//             {0,0,0,0,2}};
	}
	
	public Board(int boardLength, int boardWidth)
	{
		this.boardLength = boardLength;
		this.boardWidth = boardWidth;
	}
	
	public boolean isTileEmpty(int x, int y)
	{
		for(Tile t: tileList)
		{
			if(t.positionX == x && t.positionY == y)
			{
				return false;
			}
		}
		return true;
	}

	public void generateTiles()
	{
		
	}
	
	public void generateTilesTest()
	{
		
		
		AverageBot bot2 = new AverageBot();
		AverageBot bot1 = new AverageBot();
		
		Tile t = new Tile(TileType.BLUE, 0,0,bot1);
		t.b = this;
		Tile t2 = new Tile(TileType.RED, 30,40,bot2);
		t2.b = this;
		
		
		bot1.tile = t;
		bot2.tile = t2;
		
		//Tile t2 = new Tile(TileType.RED, 0,10);
		tileList.add(t);
		tileList.add(t2);
		//tileList.add(t2);
	}
	
	public void incrementTileTest()
	{
		tileList.get(0).positionX = tileList.get(0).positionX + 1;
		//tileList.get(1).setPositionX(tileList.get(1).getPositionX() + 1);
	}
	
//	public void moveTile(TileDirection direction, Tile tile)
//	{
//		if(direction == TileDirection.UP)
//		{
//			tile
//		}
//	}
	
	public void nextTurn()
	{
		for(Tile tile: tileList)
		{
			if(tile.tileType == nextTurn)
			{
				Action action = tile.getNextAction();
				action.performAction(this);
			}
		}
		if(nextTurn == TileType.BLUE)
		{
			nextTurn = TileType.RED;
		}
		else if(nextTurn == TileType.RED)
		{
			nextTurn = TileType.BLUE;
		}
	}
	
}
