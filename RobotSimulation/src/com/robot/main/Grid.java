package com.robot.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.engine.core.Board;
import com.engine.core.GridPainter;
import com.engine.core.StringRectangle;
import com.engine.core.Tile;

public class Grid {

	public static Board b = new Board(50,50);
	//http://stackoverflow.com/questions/15421708/how-to-draw-grid-using-swing-class-java-and-detect-mouse-position-when-click-and
    public static void main(String[] args) {
        new Grid();
    }

    public Grid() {
    	b.generateTilesTest();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                try {
					frame.add(new TestPane());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

    	GridPainter gp = new GridPainter();
        private int columnCount = b.boardWidth;
        private int rowCount = b.boardLength;
        private List<StringRectangle> cells;
        private Point selectedCell;

        public TestPane() throws Exception {
            cells = new ArrayList<>(columnCount * rowCount);
            MouseAdapter mouseHandler;
            mouseHandler = new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    Point point = e.getPoint();

                    int width = getWidth();
                    int height = getHeight();

                    int cellWidth = width / columnCount;
                    int cellHeight = height / rowCount;

                    int column = e.getX() / cellWidth;
                    int row = e.getY() / cellHeight;

                    selectedCell = new Point(column, row);
                    repaint();

                }
                
            };
            ActionListener taskPerformer = new ActionListener() {
            	  public void actionPerformed(ActionEvent evt) {
            		  b.nextTurn();
            	    repaint();
            	  }
            	};
            new Timer(200, taskPerformer).start();
            //addMouseMotionListener(mouseHandler);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(800, 800);
        }

        @Override
        public void invalidate() {
            cells.clear();
            selectedCell = null;
            super.invalidate();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int width = getWidth();
            int height = getHeight();

            int cellWidth = width / columnCount;
            int cellHeight = height / rowCount;

            int xOffset = (width - (columnCount * cellWidth)) / 2;
            int yOffset = (height - (rowCount * cellHeight)) / 2;

            if (cells.isEmpty()) {
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < columnCount; col++) {
                    	StringRectangle cell = new StringRectangle("",
                                xOffset + (col * cellWidth),
                                yOffset + (row * cellHeight),
                                cellWidth,
                                cellHeight);
                        cells.add(cell);
                    }
                }
            }
            for(Tile t: b.tileList)
            {
            	int index = t.positionX + (t.positionY * b.boardWidth);
                StringRectangle cell = cells.get(index);
               // cell.setLabel(t.bot.health);
            	gp.updateGrid(g2d, cell, b.boardWidth, t);
            }
            	
           

//            if (selectedCell != null) {
//
//                int index = selectedCell.x + (selectedCell.y * columnCount);
//                Rectangle cell = cells.get(index);
//                g2d.setColor(Color.BLUE);
//                g2d.fill(cell);
//
//            }
//            for(Tile t: b.tileList)
//            {
//            	int index = t.getPositionX() + (t.getPositionY() * b.getBoardWidth());
//            	Rectangle cell = cells.get(index);
//            	g2d.setColor(Color.BLUE);
//                g2d.fill(cell);
//            }
            

            g2d.setColor(Color.GRAY);
            for (Rectangle cell : cells) {
                g2d.draw(cell);
            }

            g2d.dispose();
        }
    }
}

