/*Ninja of Candy Collector*/
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.beans.FeatureDescriptor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;


public class Ninja implements ObjectIntf, MouseListener, Runnable{//KeyListener{

	int x,y,spritesize=100;
	GamePanel p;
	Image standingFrame;
	List<Image> randMoodFrames = new ArrayList<Image>();
	List<Image> candyMoodFrames = new ArrayList<Image>();
	List<Image> noIpMoodFrames = new ArrayList<Image>();
	int spriteCnt=0; int twice=0;
	State currentState = State.STANDING;
	Direction currentDirection = Direction.RIGHT;
	SpriteSheets ss;
	Random rnd=new Random();
	boolean randflagR=false,randflagL=false;
	
	public Ninja(GamePanel p) {
		this.p = p;
		x = 100;
		y = 550 ;
		
		try {
			ss=new SpriteSheets((BufferedImage)ImageIO.read(new File("img/game - Copy.png")));
			BufferedImage temp;
			standingFrame = (Image)ss.grabSprite(0, 0, 100, 97);
		
			//grab sprites 
			ss=new SpriteSheets((BufferedImage)ImageIO.read(new File("img/02.png")));
			{
				temp=null;
				temp=ss.grabSprite(0, 0, 96, 96); candyMoodFrames.add((Image)temp);//1
				temp=ss.grabSprite(96, 2, 101, 197); candyMoodFrames.add((Image)temp); //2
				temp=ss.grabSprite(197, 2, 103, 301); candyMoodFrames.add((Image)temp);//3
				temp=ss.grabSprite(301, 2, 100, 405); candyMoodFrames.add((Image)temp);//4
				temp=ss.grabSprite(405, 0, 101, 525); candyMoodFrames.add((Image)temp);//5
				temp=ss.grabSprite(525, 0, 102, 636); candyMoodFrames.add((Image)temp);//6
				temp=ss.grabSprite(636, 0, 100, 737); candyMoodFrames.add((Image)temp);//7
				temp=ss.grabSprite(737, 0, 102, 843); candyMoodFrames.add((Image)temp); //8
				temp=ss.grabSprite(843, 0, 103, 945); candyMoodFrames.add((Image)temp); //9
				temp=ss.grabSprite(945, 0, 102, 1046); candyMoodFrames.add((Image)temp);//10
				temp=ss.grabSprite(1046, 0, 100, 1149);   candyMoodFrames.add((Image)temp);//11
				temp=ss.grabSprite(1149, 0, 102, 1257); candyMoodFrames.add((Image)temp);//12
				temp=ss.grabSprite(1257, 0, 99, 1367); candyMoodFrames.add((Image)temp);//13
				temp=ss.grabSprite(1367, 0, 95, 1476); candyMoodFrames.add((Image)temp);//14
			
			}
			
			
			ss=new SpriteSheets((BufferedImage)ImageIO.read(new File("img/01.png")));
			{
				temp=null;
				temp=ss.grabSprite(0, 0, 96, 99); randMoodFrames.add((Image)temp);//1
				temp=ss.grabSprite(96, 0, 107, 193); randMoodFrames.add((Image)temp); //2
				temp=ss.grabSprite(193, 0, 106, 290); randMoodFrames.add((Image)temp);//3
				temp=ss.grabSprite(290, 0, 103, 388); randMoodFrames.add((Image)temp);//4
				temp=ss.grabSprite(388, 0, 103, 485); randMoodFrames.add((Image)temp);//5
				temp=ss.grabSprite(485, 0, 100, 581); randMoodFrames.add((Image)temp);//6
				temp=ss.grabSprite(581, 0, 105, 685); randMoodFrames.add((Image)temp);//7
				temp=ss.grabSprite(685, 0, 103, 789); randMoodFrames.add((Image)temp); //8
				temp=ss.grabSprite(789, 0, 102, 887); randMoodFrames.add((Image)temp); //9
				temp=ss.grabSprite(887, 0, 105, 981); randMoodFrames.add((Image)temp);//10
				temp=ss.grabSprite(981, 0, 103, 1075);   randMoodFrames.add((Image)temp);//11
				temp=ss.grabSprite(1075, 0, 106, 1169); randMoodFrames.add((Image)temp);//12
				temp=ss.grabSprite(1169, 0, 102, 1269); randMoodFrames.add((Image)temp);//13
				temp=ss.grabSprite(1264, 0, 99, 1361); randMoodFrames.add((Image)temp);//14
				temp=ss.grabSprite(1361, 0, 96, 1462); randMoodFrames.add((Image)temp);//15
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.addMouseListener(this);
		new Thread(this).start();
	}
	

	//temp draw
	@Override
	public void draw(Graphics2D g) {
			Image tempImg=null;
			AffineTransform af = new AffineTransform();
			tempImg=standingFrame;
			int check=rnd.nextInt()%100;
			if(x>=p.getWidth()-100 || x<=0){if(check<50)randflagL=true; else randflagR=true;}
			
			if(currentState==State.FEEDING){
				/*tempImg=standingFrame;
				af.translate(x, y);	*/
				if(twice<10*15){
				   tempImg = candyMoodFrames.get(spriteCnt);
			    	spriteCnt = (spriteCnt + 1) %15;
			  
			    	this.twice++;
				}
				else
				{
					tempImg=standingFrame;
				}
				af.translate(x, y);
			}
			else{
					if(x<=p.getWidth()-100 && currentDirection==Direction.RIGHT){
						x+=5;
						
						if( randflagR==true){
							
							tempImg = randMoodFrames.get(spriteCnt);
							spriteCnt = (spriteCnt + 1)%15;
						}
						af.translate(x, y);	
						if(x>=p.getWidth()-100 ){
								currentDirection=Direction.LEFT;
								currentState= State.STANDING;
								af.scale(-1, 1);
								af.translate(-x-standingFrame.getWidth(null), y);
								randflagR=false;
							}
					}
						
					else if(x>=0 && currentDirection==Direction.LEFT){
						x-=5;
						if( randflagL==true){
							
							tempImg = randMoodFrames.get(spriteCnt);
							spriteCnt = (spriteCnt + 1)%15;
						}
						af.scale(-1, 1);
						af.translate(-x-standingFrame.getWidth(null), y);
						if(x==0){
							currentDirection=Direction.RIGHT;
							af.translate(x, y);
							randflagL=false;
						}
					}	
			}
					g.drawImage(tempImg,af,null);
			}
			
		
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x1=e.getX();
		int y1=e.getY();
		if( x1 <= (this.x+standingFrame.getWidth(null)) &&
			x1 >= (this.x) &&	
			y1 <= (this.y+standingFrame.getHeight(null)) &&
			y1 >= (this.y)
		  )
		{
			currentState = State.RANDOM;
			spriteCnt = 0;
		}
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}


