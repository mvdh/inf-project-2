
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author averaart
 */
public class Projectile extends Sprite {

    int damage;

    Projectile(int d) {
        super();
        damage = d;
    }

    @Override
    public void endMove() {
        System.out.println("BOEM!!!\nIk doe nu " + damage + " schade aan m'n doelwit");
        try {
            this.finalize();
        } catch (Throwable ex) {
        }
    }
}
