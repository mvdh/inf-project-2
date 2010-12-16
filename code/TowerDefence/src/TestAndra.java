

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.JFrame;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author averaart
 */
public class TestAndra extends JFrame {

    private Projectile s1;
    private Projectile s2;
    private Projectile s3;
    private Projectile s4;
    private Projectile s5;
//    private Sprite s6;
//    private Sprite s7;
//    private Sprite s8;
//    private Sprite s9;
//    private Sprite s10;
    ArrayList<Sprite> spriteList;

    public TestAndra(String string) {
        
        init();
        s1 = new Projectile(1);
        add(s1);
        s2 = new Projectile(2333);
        add(s2);
        s3 = new Projectile(5);
        add(s3);
        s4 = new Projectile(9999);
        add(s4);
        s5 = new Projectile(1);
        add(s5);
//        s6 = new Sprite(1.0000000000000001);
//        add(s6);
//        s7 = new Sprite(1.5);
//        add(s7);
//        s8 = new Sprite(3.33333333333000000011);
//        add(s8);
//        s9 = new Sprite(4.0);
//        add(s9);
//        s10 = new Sprite(5.0);
//        add(s10);
        spriteList = new ArrayList<Sprite>();
        spriteList.add(s1);
        spriteList.add(s2);
        spriteList.add(s3);
        spriteList.add(s4);
        spriteList.add(s5);
//        spriteList.add(s6);
//        spriteList.add(s7);
//        spriteList.add(s8);
//        spriteList.add(s9);
//        spriteList.add(s10);
        init2();
        paintAll(getGraphics());

        
    }

    public void init() {
        setDefaultCloseOperation(3);
        setSize(500, 300);
        setVisible(true);
        setLayout(null);

        
    }

    public void init2(){
        int delay = 4; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                for (Sprite s : spriteList){
                    s.step();
                }
            }
        };
        new Timer(delay, taskPerformer).start();

    }

    public static void main(String[] args) {
        new TestAndra("FrameDemo");


    }
}
