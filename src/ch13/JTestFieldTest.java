package ch13;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class JTestFieldTest extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTestFieldTest(){
		super();
		setTitle("Position");
		Container c = getContentPane();
//		c.setLayout(new GridLayout(3, 2, 10, 10));
		c.setLayout(new FlowLayout(1, 20, 20));
		final JTextField jt = new JTextField("aaa", 20);
		final JButton jb = new JButton("Clear");
		
		jt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jt.setText("Action happened");
				
			}
		});
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jt.setText("");
				jt.requestFocus();
			}
		});
		jb.setSize(20, 40);
		c.add(jt);
		c.add(jb);
		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new JTestFieldTest();

	}

}
