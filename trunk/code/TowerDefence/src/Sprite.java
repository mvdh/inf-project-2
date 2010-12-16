
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Sprite extends JLabel {

    Point c = new Point();          // Start Positie
    Point d = new Point(300, 200);  // Bestemming
    double speed = 0.1;
    int stepCounter = 0;
    int marge = 2;

    public Sprite() {
        ImageIcon img = new ImageIcon(getClass().getResource("spriteDefault.png"));
        setIcon(img);
        setSize(img.getIconWidth(), img.getIconHeight());
        int x = (int) (Math.random() * 480);
        int y = (int) (Math.random() * 280);
        setLocation(x, y);
        c = getLocation();
    }

    public Sprite(double s){
        this();
        speed = s;
        marge = (int)Math.ceil(s)+1;
    }

    public void step() {

        // Hou hier rekening met een modifier voor vertragende torens.

        Double dis = distance(this.getLocation(),d); // Wordt gebruikt om de te reizen afstand voor deze stap door te geven
        if (dis > speed){                            // Op deze manier wordt 'overshoot' beperkt
            dis = speed;                             // en kan de marge hopelijk kleiner
        }

   
        double angle = Math.atan2(d.getY() - c.getY(), d.getX() - c.getX()); // Berekent de hoek waaronder gereisd wordt in radialen

        int newX = (int) Math.floor(c.getX() + (Math.cos(angle) * (dis*stepCounter)));
        int newY = (int) Math.floor(c.getY() + (Math.sin(angle) * (dis*stepCounter)));





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

        stepCounter++;

    }

    private void setNewDestination() {
        c = this.getLocation();
        stepCounter = 0;
        int x = (int) (Math.random() * 480);
        int y = (int) (Math.random() * 280);
        d.setLocation(x, y);
        System.out.println(c);
        System.out.println(d);
    }

    private double distance(Point p, Point q) {     //Calculates distance between two points
        double dx = p.x - q.x;                      //horizontal difference
        double dy = p.y - q.y;                      //vertical difference
        double dist = Math.sqrt(dx * dx + dy * dy); //distance using Pythagoras theorem
        return dist;
    }
}
