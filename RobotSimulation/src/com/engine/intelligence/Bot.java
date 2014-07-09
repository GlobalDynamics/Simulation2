package com.engine.intelligence;

import com.engine.actions.Action;

public abstract class Bot {
	
	public abstract Action nextAction();
	
	public abstract String getRepresentationString();
	
	public abstract void decrementHealth(int amount);
	
	public abstract int getHealth();
}
