

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Sprite extends JLabel {

    Point c = new Point();  // Huidige positie
    Point d = new Point(300,200);  // Bestemming
    Double speed = 16.0;
    int marge = 8;

    public Sprite() {
        ImageIcon img = new ImageIcon(getClass().getResource("spriteDefault.png"));
        setIcon(img);
        setSize(img.getIconWidth(), img.getIconHeight());
        setLocation(100, 100);
    }

    public void step() {
        c = this.getLocation();
        // Hou hier rekening met een modifier voor vertragende torens.
        double angle = Math.atan2(d.getY() - c.getY(), d.getX() - c.getX()); // Berekent de hoek waaronder gereisd wordt in radialen
        int newX = (int) (c.getX()+(Math.cos(angle) * speed));
        int newY = (int) (c.getY()+(Math.sin(angle) * speed));

        System.out.println(c);
        System.out.println(d);
        System.out.println(angle);
        System.out.println(newX);
        System.out.println(newY);




        // Om iets zekerder te zijn dat het object niet over het doel heen schiet is er een marge ingebouwd waarop ie 'snapped'
        if ((newX > d.getX() - marge) && (newX < d.getX() + marge)) {
            newX = (int) d.getX();
        }

        if ((newY > d.getY() - marge) && (newY < d.getY() + marge)) {
            newY = (int) d.getY();
        }

        this.setLocation(newX, newY);
//        this.repaint();

        if ((newX == (int) d.getX()) && (newY == (int) d.getY())) {
//            d.end();                                           // Wat gebeurt er wanneer een beweging voltooid is
//            this.finalize();                                      // Is opruimen na de beweging nodig?
            System.out.println("Beweging voltooid");
            setNewDestination();
        }

    }

    private void setNewDestination(){
        int x = (int)(Math.random()*480);
        int y = (int)(Math.random()*280);
        d.setLocation(x, y);
    }

}
