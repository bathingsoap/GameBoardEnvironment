package gbe.gui;

import gbe.engine.*;
import gbe.players.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.*;

public class gbeGUI {
	
	// create panel classes
	
	// menu:
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
	private State state;
	private GameLogic logic;
	private GameEngine engine;
	
	public gbeGUI(GameEngine e, State s, GameLogic l) {
		this.engine = e;
		this.logic = l;
		this.state = s;
	}
}
