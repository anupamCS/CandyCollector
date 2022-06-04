import java.awt.Point;


public class Score {
		int val=0;
		GamePanel p;
		int frogIndex,candyIndex;
		Point frg; // frg point
		ObjectIntf o ;
		long startTime ; // Get the start Time
		long endTime = 0; 
		
		public Score(GamePanel p){
			this.p=p;
			startTime = System.currentTimeMillis();
			for(int i=0;i<p.objects.size();i++){
				o=p.objects.get(i) ;			
				if(o instanceof Ninja){
					frogIndex=i;
				}
				else if(o instanceof Candy)
				{
					candyIndex=i;
				}
			}
		}
		public boolean feedCandy(Point p1)
		{
			int x1=p1.x;
			int y1=p1.y;
			int elapsed;
			Ninja w=(Ninja)p.objects.get(frogIndex);
			//Candy c=(Candy)p.objects.get(candyIndex);
			if( x1 <= (w.x+w.standingFrame.getWidth(null)) &&
					x1 >= (w.x) &&	
					y1 <= (w.y+w.standingFrame.getHeight(null)) &&
					y1 >= (w.y)
				  )
			{
				endTime = System.currentTimeMillis(); //Get the end Time
				w.currentState=State.FEEDING;
				w.spriteCnt=0;
			//	System.out.println("frog: "+p1);
			//	c.eaten=true;
				
				elapsed=(int)(endTime-startTime)/1000;
				this.val=100-(5*elapsed);
				val=val<0?0:val;
				System.out.println("SCORE:"+val);
				return true;
			}
			return false;
		}
		public boolean getScore(int ballX, int ballY){
			Point p1=new Point();
			p1.x=ballX;
			p1.y=ballY;
			boolean fed=feedCandy(p1);			
			return fed;
		}
		public int getVal(){return this.val;}
}
