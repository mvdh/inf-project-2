import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Slayer
 */
public class StartPanel extends Panel implements ActionListener
{

	private JButton start;

	public StartPanel(GameStats gameStats)
	{
		super(gameStats);
		start = new JButton("Start Game!");
		start.setFont(super.getFont());
		start.setForeground(Color.black);
		start.setBackground(Color.white);
		start.setBounds(210, 240, 180, 20);
		start.addActionListener(this);
		this.add(start);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(start))
		{
			getGameStats().game.updateStats();
			getGameStats().setStarted(true);
			setVisible(false);
		}
	}
}
