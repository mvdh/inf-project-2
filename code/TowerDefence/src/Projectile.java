
import java.awt.Point;
import javax.swing.ImageIcon;

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

    public Projectile(int d, int img, double speed, int range, Point destination, Point start)
    {
        super(speed);
        damage = d;
        this.range = range;
        this.d = destination;
        this.c = start;
        switch (img)
        {
            case 0:
            {
                this.setIcon(new ImageIcon(getClass().getResource("arrow.png")));
            }
            case 1:
            {
                this.setIcon(new ImageIcon(getClass().getResource("spriteDefault.png")));
            }
        }
        setSize(this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
    }

    @Override
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
