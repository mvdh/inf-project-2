
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author averaart
 */
public class Projectile extends Sprite
{

    private int damage;
    private int range;

    private BufferedImage bf;
    
    public Projectile(int d, int img, double speed, int range, Point destination, Point start)
    {
        super(speed);
        damage = d;
        this.range = range;
        this.d = destination;
        this.c = start;
        URL url = null;
        
        switch (img)
        {
            case 0:
            {
                url = getClass().getResource("arrow.png");
            }
            case 1:
            {
                url = getClass().getResource("spriteDefault.png");
            }
        }
        try {
            bf = ImageIO.read(url);
        } catch (Exception e) {}
        setImage(bf);
        
        int size = Math.max(bf.getHeight(), bf.getWidth());
        setSize(size, size);
        setLocation(start);
    }

    public void endMove()
    {
        //System.out.println("BOEM!!!\nIk doe nu " + damage + " schade aan m'n doelwit");
        try
        {
            this.finalize();
        } catch (Throwable ex)
        {
            ex.printStackTrace();
        }
    }

    public int getDamage()
    {
        return damage;
    }

    public int getRange()
    {
        return range;
    }
}