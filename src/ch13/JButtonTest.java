package ch13;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class JButtonTest extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JButtonTest(){
		URL url = MyImageIcon.class.getResource("goku.jpg");
		Icon icon = new ImageIcon(url);
		setLayout(new GridLayout(3, 2, 5, 5));
		Container c = getContentPane();
		for (int i = 0; i < 5; i++) {
			JButton jb = new JButton("Buttion" + i, icon);
			c.add(jb);
			if (i % 2 == 0) {
				jb.setEnabled(false);
			}
		}
		JButton jb = new JButton();
		jb.setMaximumSize(new Dimension(90, 30));
		jb.setIcon(icon);
		jb.setHideActionText(true);
		jb.setToolTipText("Picture button");
		jb.setBorderPainted(false);
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Show messege");
				
			}
		});
		c.add(jb);
		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new JButtonTest();

	}

}
