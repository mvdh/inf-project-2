
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Slayer
 */
public class EndPanel extends Panel implements ActionListener
{

        private JButton restart;
        private JPanel description;
        private JLabel score;
        private JLabel wave;

        public EndPanel(GameStats gameStats)
        {
                super(gameStats);

                description = new JPanel(new GridLayout(2, 1));

                score = new JLabel("Score: " + super.getGameStats().getPoints());
                wave = new JLabel("Survived untill wave: " + (super.getGameStats().getWave() + 1));

                score.setFont(super.getFont());
                score.setForeground(Color.white);
                wave.setFont(super.getFont());
                wave.setForeground(Color.white);

                description.setBackground(Color.black);
                description.setLocation(200, 180);
                description.setSize(200, 40);

                description.add(score);
                description.add(wave);

                restart = new JButton("Try again");
                restart.setFont(super.getFont());
                restart.setForeground(Color.black);
                restart.setBackground(Color.white);
                restart.setBounds(240, 240, 120, 20);

                add(restart);
                add(description);
        }

        public void actionPerformed(ActionEvent e)
        {
                if (e.getSource().equals(restart))
                {
                        super.getGameStats().setStarted(true);
                        setVisible(false);
                }
        }
}
