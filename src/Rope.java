import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;



public class Rope implements ObjectIntf, Runnable{
		int x1,y1,x2,y2;
	
		GamePanel p;
		int flag;
		Image pic;
		
		public Rope(int x1, int y1,int x2, int y2,GamePanel p){
			this.p=p;
			this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
			flag=1;
			new Thread(this).start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void draw(Graphics2D g) {
			// TODO Auto-generated method stub
			 g.setColor(Color.yellow);
			 g.drawLine(x1, y1, x2, y2);
			 g.setColor(Color.orange);
	         g.drawLine(x1-1, y1, x2-1, y2);
	         g.setColor(Color.yellow);
	         g.drawLine(x1, y1, x2, y2);
	         g.setColor(Color.orange);
	         g.drawLine(x1+1, y1, x2+1, y2);
	       //  g.drawRect(x1, y1, 1, (int) Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1,2)));
		}
		
}
