package com.fengshun.tank;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import com.fengshun.tank.Tank.Direction;

import java.util.ArrayList;
/**
 * This class is the main window of this tank game.
 * @author Zang Fengshun
 *
 */
public class TankClient extends Frame {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    
	Tank myTank = new Tank(600, 500, true, Tank.Direction.STOP, this);
	
	Wall w1 = new Wall(300, 300, 20, 150, this), w2 = new Wall(400, 100, 300, 20, this);
	
	List<Explode> explodes = new ArrayList<Explode>();
	List<Missile> missiles = new ArrayList<Missile>();
	List<Tank> tanks = new ArrayList<Tank>();
	
	Image offScreenImage = null;
	
	Blood b = new Blood();
	
	public void paint(Graphics g) {
		g.drawString("missiles count: " + missiles.size(), 10, 60);
		g.drawString("explodes count: " + explodes.size(), 10, 80);
		g.drawString("tanks count: " + tanks.size(), 10, 100);
		g.drawString("tanks life: " + myTank.getLife(), 10, 120);
		
		if(tanks.size() <= 0) {
			for(int i=0; i<5; i++) {
				tanks.add(new Tank(50 + 40*(i+1), 50, false, Tank.Direction.D, this ));
			}
		}
		
		for(int i=0; i<missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.hitTanks(tanks);
			m.hitTank(myTank);
			//if(!m.isLive()) missiles.remove(m);
			//else m.draw(g);
			m.hitWall(w1);
			m.hitWall(w2);
			m.draw(g);
		}
		
		for(int i=0; i<explodes.size(); i++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}
		
		for (int i=0; i<tanks.size();i++) {
			Tank t = tanks.get(i);
			t.collidesWithWall(w1);
			t.collidesWithWall(w2);
			t.collidesWithTanks(tanks);
			t.draw(g);
		}
		myTank.draw(g);
		myTank.eat(b);
		w1.draw(g);
		w2.draw(g);
		b.draw(g);
		myTank.collidesWithWall(w1);
		myTank.collidesWithWall(w2);
	}
	
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createVolatileImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void launchFrame() {
		
		for(int i=0; i<10; i++) {
			tanks.add(new Tank(50 + 40*(i+1), 50, false, Tank.Direction.D, this ));
		}
		
    	this.setLocation(400, 300);
    	this.setSize(GAME_WIDTH, GAME_HEIGHT);
    	this.setTitle("TankWar");
    	this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
    	});
    	this.setResizable(false);
    	this.setBackground(Color.GREEN);
    	
    	this.addKeyListener(new KeyMonitor());
    	setVisible(true);
    	
    	new Thread(new PaintThread()).start();
    }
	
    public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
	}
    
    private class PaintThread implements Runnable {

		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
		}
    }

    private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
    	
    }
}
