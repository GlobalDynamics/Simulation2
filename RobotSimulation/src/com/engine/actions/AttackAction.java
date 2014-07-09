package com.engine.actions;

import com.engine.core.Board;
import com.engine.core.Tile;

public class AttackAction extends Action{
	
	public Tile attackingTile;
	public Tile recievingTile;

	@Override
	public void performAction(Board b) {
		attackingTile.attackTile(recievingTile);
		
	}

}
