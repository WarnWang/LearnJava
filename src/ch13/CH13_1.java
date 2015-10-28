package ch13;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

class WindowTest extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WindowTest(){
		//Create a container
		Container c = getContentPane();
//		c.setLayout(new FlowLayout(2, 10, 10));
		c.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Create a list and other component
		String[] contents = {"红", "黄", "蓝"};
		JComboBox<String>jl = new JComboBox<String>(contents);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
//		gbc.weightx = 1.0;
		c.add(jl, gbc);
		
		JCheckBox jc1 = new JCheckBox("Male", false);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.ipadx = 20;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
//		gbc.weightx = 0.0;
		c.add(jc1, gbc);

		JCheckBox jc2 = new JCheckBox("Female", false);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.ipadx = 20;
		gbc.gridy = 1;
		c.add(jc2, gbc);
		
		JButton jb1 = new JButton("确定");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipadx = 20;
		gbc.weightx = 0.0;
		c.add(jb1, gbc);
		
		JButton jb2 = new JButton("取消");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		c.add(jb2, gbc);
//		c.add(BorderLayout.NORTH, jl);
//		c.add(BorderLayout.CENTER, jc1);
//		c.add(BorderLayout.CENTER, jc2);
//		c.add(BorderLayout.SOUTH, jb1);
//		c.add(BorderLayout.SOUTH, jb2);
		setSize(200, 200);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}

public class CH13_1 {

	public static void main(String[] args) {
		new WindowTest();

	}

}
