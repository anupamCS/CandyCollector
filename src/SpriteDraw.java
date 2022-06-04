/*SpriteDraw of Candy Collector*/
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class SpriteDraw extends JPanel{

	public void getSprites(Graphics g){
		try {
			BufferedImage bigimg= ImageIO.read(new File("img/ninja.png"));
			final int width = 64;
	        final int height = 64;

	        int x = 0;
	        int y = 0;
	        Image subimg= bigimg.getSubimage(x, y, width, height);
	        g.drawImage(subimg, 5, 5, this);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
