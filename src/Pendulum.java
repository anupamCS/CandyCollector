/*Pendulum of Candy Collector*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pendulum extends JPanel implements ObjectIntf, MouseListener, //,Runnable
		MouseMotionListener, KeyListener, ActionListener {

	GamePanel p;
	private double angle = Math.PI / 4;
	private int length;
	boolean dragged;
	Rope rp;
	Candy cd;	
	int anchorX, anchorY, ballX, ballY;
	Point startPoint, endPoint, intersectPoint;	
	double angleAccel, angleVelocity = 0, dt = 0.25, gravity=5.0;
	boolean oscillating = true,fed=false, invisible=false;	
	JLabel score;
	Timer gameTimer;
	Score sr_c;

	public Pendulum(int length,JLabel score, GamePanel p) {
		this.p = p;
		this.length = length;
		setDoubleBuffered(true);
		dragged = false;
		this.score=score;
		// add mouse listners
		p.addMouseListener(this);
		p.addMouseMotionListener(this);
		//add key listner
		p.addKeyListener(this);

		intersectPoint = new Point();
		
		// setting focus to gamepanel for the keylistner to work
		p.setFocusable(true);
		p.requestFocusInWindow();
		
		//score
		sr_c=new Score(p);
		
		//new Thread(this).start();
	    gameTimer=new Timer(15,this);
	    gameTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (oscillating) {
			angleAccel = -9.81 / length * Math.sin(angle); // 9.81 is g
			angleVelocity += angleAccel * dt;
			angle += angleVelocity * dt;
		}
		
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(2 * length + 50, length / 2 * 3);
	}

	double vel=10;
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
     if(!invisible){
		if (oscillating) {
			Color brown = new Color(128, 128, 0);
			anchorX = p.getWidth() / 2;
			anchorY = p.getHeight() / 10;
			ballX = anchorX + (int) (Math.sin(angle) * length); // l*sin0
			ballY = anchorY + (int) (Math.cos(angle) * length); //l*cos0

			rp = new Rope(anchorX, anchorY, ballX, ballY, p);
			rp.draw(g);
		}
		else if(!invisible){
			ballX += (int) (Math.sin(angle) * vel);
			ballY += (int)(Math.cos(angle) * vel + gravity);
			
			vel-=0.1;
			vel=vel<0?0:vel; // ignore -ve vel
			
			ballY=ballY+25>p.getHeight()?p.getHeight()-25:ballY; //turn to rest
			
			if(ballY>=500) 
			{
				fed=sr_c.getScore(ballX,ballY);
				if(fed==true){ cd.setEaten(true);  invisible=true;}
				score.setText(String.valueOf(sr_c.val));
			}
		}
		cd = new Candy(ballX - 25, ballY - 25, p);
		cd.draw(g);
     }
		g.setColor(Color.blue);
		g.fillOval(anchorX - 7, anchorY - 7, 15, 15);
		g.setColor(Color.red);
		g.fillOval(anchorX - 5, anchorY - 5, 10, 10);
		

		// show cut drag line
		if (dragged && endPoint != null) {

			g.setColor(Color.RED);
			g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
		}
     
	}

	boolean get_line_intersection(float p0_x, float p0_y, float p1_x,
			float p1_y, float p2_x, float p2_y, float p3_x, float p3_y) {
		float s1_x, s1_y, s2_x, s2_y;
		
		int l1x[] = {(int) p0_x,(int) p1_x};
		int l1y[] = {(int) p0_y,(int) p1_y};
		Polygon l1 = new Polygon(l1x, l1y, 2);
		int l2x[] = {(int) p2_x,(int) p3_x};
		int l2y[] = {(int) p2_y,(int) p3_y};
		Polygon l2 = new Polygon(l2x, l2y, 2);
		
//		Rectangle l1 = new Rectangle((int)p0_x, (int)p0_y, (int)p1_x, (int)p1_y);
//		Rectangle l2 = new Rectangle((int)p2_x, (int)p2_y, (int)p3_x, (int)p3_y);
		
		
		
		/*s1_x = p1_x - p0_x;
		s1_y = p1_y - p0_y;
		s2_x = p3_x - p2_x;
		s2_y = p3_y - p2_y;

		float s, t;
		s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y))
				/ (-s2_x * s1_y + s1_x * s2_y);
		t = (s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x))
				/ (-s2_x * s1_y + s1_x * s2_y);

		if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
			// Collision detected
			intersectPoint.x = (int) (p0_x + (t * s1_x));
			intersectPoint.y = (int) (p0_y + (t * s1_y));
			return true;
		}

		return false; // No collision*/
		if(l1.intersects(l2.getBounds2D())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		startPoint = e.getPoint();
		endPoint = null;
		dragged = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		endPoint = e.getPoint();
		dragged = false;

		if (get_line_intersection(startPoint.x, startPoint.y, endPoint.x,
				endPoint.y, anchorX, anchorY, ballX, ballY)) {
			System.out.println("Intersected at: " + intersectPoint); //check int pt at consoule 
			oscillating = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (dragged) {
			endPoint = e.getPoint();
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent arg0) { //check
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if(gameTimer.isRunning())
				gameTimer.stop();
			else {
				gameTimer.start();
			}
		}		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}