package com.engine.intelligence;

import com.engine.actions.Action;

public abstract class Bot {
	
	
	//status
	public boolean stealthActive = false;
	
	public abstract Action nextAction();
}
