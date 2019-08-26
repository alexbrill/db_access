package general;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	public MainPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		JLabel guys_pic = new JLabel(new ImageIcon("imgs/guys.png"));
		JLabel text_field = new JLabel("HOSPITAL INFORMATION SYSTEM");
		text_field.setFont(text_field.getFont().deriveFont(32.0f));
		text_field.setHorizontalAlignment(JLabel.CENTER);
		
		add(text_field, BorderLayout.NORTH);
		add(guys_pic, BorderLayout.SOUTH);
	}
}
