

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

    private Sprite s;
    ArrayList<Sprite> spriteList;

    public TestAndra(String string) {
        
        init();
        s = new Sprite();
        add(s);
        spriteList = new ArrayList<Sprite>();
        spriteList.add(s);
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
        int delay = 40; //milliseconds
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
