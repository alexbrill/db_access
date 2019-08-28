package db;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel {

	public HomePanel(Dimension size) {
		setLayout(null);		
		JLabel mainPageScreen = new JLabel(new ImageIcon("imgs/mainpagescreen.png"));
		mainPageScreen.setBounds(0, 0, size.width, size.height);
		add(mainPageScreen);		
	}
}
