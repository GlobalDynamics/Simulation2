package com.engine.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.engine.actions.Action;
import com.engine.intelligence.AverageBot;
import com.engine.types.TileType;
import com.engine.util.Util;

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
	
	public void generateTilesTest2()
	{
		
		
		AverageBot bot2 = new AverageBot();
		AverageBot bot1 = new AverageBot();
		AverageBot bot3 = new AverageBot();
		
		Tile t = new Tile(TileType.BLUE, 0,0,bot1);
		t.b = this;
		Tile t2 = new Tile(TileType.RED, 30,40,bot2);
		t2.b = this;
		Tile t3 = new Tile(TileType.RED, 49,8,bot3);
		t3.b = this;
		
		
		bot1.tile = t;
		bot2.tile = t2;
		bot3.tile = t3;
		
		//Tile t2 = new Tile(TileType.RED, 0,10);
		tileList.add(t);
		tileList.add(t2);
		tileList.add(t3);
		//tileList.add(t2);
	}
	
	public void generateTilesTest()
	{
		for(int i =0;i<=4;i++)
		{
			int width = Util.randInt(0, this.boardWidth);
			int length = Util.randInt(0, this.boardWidth);
			AverageBot bot = new AverageBot();
			Tile t = new Tile(TileType.BLUE, width,length,bot);
			t.b = this;
			bot.tile = t;
			tileList.add(t);
		}
		
		for(int i =0;i<=4;i++)
		{
			int width = Util.randInt(0, this.boardWidth);
			int length = Util.randInt(0, this.boardWidth);
			AverageBot bot = new AverageBot();
			Tile t = new Tile(TileType.RED, width,length,bot);
			t.b = this;
			bot.tile = t;
			tileList.add(t);
		}
		
		
	}
	
	public void incrementTileTest()
	{
		tileList.get(0).positionX = tileList.get(0).positionX + 1;
		//tileList.get(1).setPositionX(tileList.get(1).getPositionX() + 1);
	}
	
	public Tile getTileByCoordinates(double x, double y)
	{
		for(Tile t : tileList)
		{
			if(t.positionX == x && t.positionY == y)
			{
				return t;
			}
		}
		return null;
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
		
		for (Iterator<Tile> iter = tileList.listIterator(); iter.hasNext(); ) {
		    Tile tile = iter.next();
			if(tile.tileType == nextTurn)
			{
				if(tile.bot.getHealth() <= 0)
				{
					iter.remove();
					continue;
				}
				Action action = tile.getNextAction();
				action.performAction(this);

			}

		}
		
		//for(Tile tile: tileList)
		//{

		//}
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
