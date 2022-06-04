import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Candy implements ObjectIntf, Runnable{
		int x;
		int y;
		GamePanel p;
		Image pic;
		boolean eaten;
		
		public Candy(int x, int y,GamePanel p){
			this.p=p;
			this.x=x;
			this.y=y;
			try {
				pic=ImageIO.read(new File("img/candy.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			eaten=false;
			new Thread(this).start();
		}
		public void setEaten(boolean val){this.eaten=val;}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void draw(Graphics2D g) {
			// TODO Auto-generated method stub
			if(eaten==false){
				Image temp= pic;
				g.drawImage(temp,x,y,null);
			}
		}
		
}
