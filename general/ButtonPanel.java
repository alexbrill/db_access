package general;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	
	CardLayout cardLayout;
	RightPanel control_panel;

	public ButtonPanel(CardLayout cL, RightPanel control_panel2) {
		cardLayout = cL;
		control_panel = control_panel2;
		setBackground(new Color(135, 127, 127));
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxlayout);
		setPreferredSize(new Dimension(167, 650));
		
		ImageIcon home_icon = new ImageIcon("imgs/main_btn.png");
		ImageIcon pat_icon = new ImageIcon("imgs/pat_btn.png");
		ImageIcon doc_icon = new ImageIcon("imgs/doc_btn.png");
		ImageIcon sched_icon = new ImageIcon("imgs/sched_btn.png");	 
		
		JButton home_btn = new JButton(home_icon);
		JButton pat_btn = new JButton(pat_icon);
		JButton doc_btn = new JButton(doc_icon);
		JButton sched_btn = new JButton(sched_icon);
		
		home_btn.setContentAreaFilled(false);
		pat_btn.setContentAreaFilled(false);
		doc_btn.setContentAreaFilled(false);
		sched_btn.setContentAreaFilled(false);
		
		add(home_btn);
		add(pat_btn);
		add(doc_btn);
		add(sched_btn);
		
		home_btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(control_panel, "1");
            }
        });

        pat_btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(control_panel, "2");
            }
        });
        
        doc_btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(control_panel, "3");
            }
        });
        
        sched_btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(control_panel, "4");
            }
        });
	}
}
