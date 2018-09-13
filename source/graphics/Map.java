package escala.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;
import escala.GameState;
import java.awt.*;
import javax.swing.JFrame;

/*
 * 
 * NOTE::: if map is not rendered properly, double check path and names:
 * 
 * TODO::: Ideally, we will pull NUM_REGIONS and regionNames from database
 *              regionNames can be stored as paths...
 * 
 *      Use cursor location to determine which region to highlight
 * 
 * NOTE::: To improve game performance, reduce image size and pre-stretch all images when loading.
 * */

public class Map {
    
    private static final int NUM_REGIONS = 10;
    BufferedImage background = null;
    BufferedImage[] regions = new BufferedImage[NUM_REGIONS];
    String[] regionNames = {"Asia", "EasternEurope", "LatinAmerica", "MiddleEast", 
            "NorthAfrica", "NorthAmerica", "Ocenia", "SouthAfrica", "SouthAmerica", "WesternEurope"};
    BufferedImage[] glowRegions = new BufferedImage[NUM_REGIONS];
    
    int imageWidth = 1152;
    int imageHeight = 648;
    
    
    public Map(){
        try {
            URL url = getClass().getResource("/data/assets/Background.png");
            background = ImageIO.read(new File(url.getPath()));
            
            //load all regions
            for(int i = 0; i < NUM_REGIONS; i++) {
                url = getClass().getResource("/data/assets/" + regionNames[i] + ".png"); 
                regions[i] = ImageIO.read(new File(url.getPath()));
            }
            
            //load all glow regions
            for(int i = 0; i < NUM_REGIONS; i++) {
                url = getClass().getResource("/data/assets/" + regionNames[i] + "Glow.png"); 
                glowRegions[i] = ImageIO.read(new File(url.getPath()));
            }
            
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }

    public void renderMap(Graphics2D g) {
        GameState myGame = GameState.getInstance();


        //Determines which region should be highlighted 
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point r = myGame.getFrame().getLocation();

        int skip = regionSelect(new Point((p.x - r.x),(p.y - r.y - 23)),myGame.getScale());

        System.out.println("(" + (p.x - r.x) + 
              ", " + 
              (p.y - r.y - 23) + ")");
        
        // render background
        if(background != null)
            //g.drawImage(Image, dstx1, dsty1, dstx2, dsty2, srcx1, srcy1, srcx2, srcy2, observer)
            g.drawImage(background, 0, 0, myGame.getWidth(), myGame.getHeight(), 0, 0, imageWidth, imageHeight, null);
        else{
            g.setBackground(Color.BLACK);
            g.clearRect(0, 0, myGame.getWidth(), myGame.getHeight());
        }
        
        // render each region
        for(int i = 0; i < NUM_REGIONS; i++)
        {
            if(i == skip)
                continue;

            g.drawImage(regions[i], 0, 0, myGame.getWidth(), myGame.getHeight(), 0, 0, imageWidth, imageHeight, null);
        }

        if(skip < NUM_REGIONS)
            g.drawImage(glowRegions[skip], 0, 0, myGame.getWidth(), myGame.getHeight(), 0, 0, imageWidth, imageHeight, null);
        
    }

    private int regionSelect(Point p, double scale)
    {
        //TODO Replace with Polygons for higher accuracy 
        Rectangle [] regionsRect = {
            new Rectangle(819, 166, 1036, 335),
            new Rectangle(624, 75, 1049, 182),
            new Rectangle(113, 230, 284, 307),
            new Rectangle(641, 165, 782, 308),
            new Rectangle(450, 219, 721, 357),
            new Rectangle(16, 38, 401, 257),
            new Rectangle(882, 336, 1443, 540),
            new Rectangle(567, 350, 717, 510),
            new Rectangle(213, 334, 394, 590),
            new Rectangle(505, 64, 599, 208)};

        //To be tested, Scale might not be working
        //Change point to doubles for polygons
        p = new Point((int)(p.x * scale), (int)(p.y * scale));

        for(int i = 0; i < NUM_REGIONS; i++)
            if(regionsRect[i].contains(p))
                return i;

        return Integer.MAX_VALUE;
    }

}
