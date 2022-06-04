/*GamePanel of Candy Collector*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	
	List<ObjectIntf> objects = new ArrayList<ObjectIntf>();
	Image bgImg;
	
	boolean drag=false;
	
	public GamePanel() {
		try {
			bgImg=ImageIO.read(new File("img/bgback.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Timer(15,this).start();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);	
		g.drawImage(bgImg,0,0,getWidth(),getHeight(),null);
		
		for(ObjectIntf o : objects)
			o.draw((Graphics2D)g);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();		
	}

}
