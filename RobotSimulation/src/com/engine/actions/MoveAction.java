package com.engine.actions;

import com.engine.core.Board;
import com.engine.core.Tile;
import com.engine.types.TileDirection;

public class MoveAction extends Action {
	
	public TileDirection direction;
	public Tile tile;

	@Override
	public void performAction(Board b) {
		tile.moveTile(direction);
	}

}
