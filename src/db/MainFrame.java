package db;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	public MainFrame(String title) {
		super(title);
		Dimension size = new Dimension(800, 650);
		setPreferredSize(size);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		ControlPanel controlpanel;
		try {
			controlpanel = new ControlPanel(size);
			add(controlpanel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame("HOSPITAL INFORMATION SYSTEM");
	}
}
