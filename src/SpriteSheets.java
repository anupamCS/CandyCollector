/*SpriteSheet of Candy Collector */
import java.awt.image.BufferedImage;


public class SpriteSheets {
	
		public BufferedImage spritesheets;
		
		public SpriteSheets(BufferedImage originalimg){
			this.spritesheets=originalimg;
					
		}
		
		public BufferedImage grabSprite(int x, int y, int height, int wx){
			int width=wx-x;
			BufferedImage sprite= spritesheets.getSubimage(x, y, width, height);
			return sprite;
			
		}
}
