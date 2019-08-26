package general;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MainForm extends JFrame {

	CardLayout cardLayout = new CardLayout();
	
	public MainForm(String title)  {
		super(title);
		setPreferredSize(new Dimension(800, 650));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
				
		RightPanel control_panel = new RightPanel(cardLayout);
		ButtonPanel button_panel = new ButtonPanel(cardLayout, control_panel);
		
		add(control_panel, BorderLayout.EAST);
		add(button_panel, BorderLayout.CENTER);

		pack();
		setVisible(true);		
	}
	
	
	public static void main(String[] args){

		MainForm form = new MainForm("");
	}
}
