package ch13;

import java.awt.Container;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MyImageIcon extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyImageIcon(){
		super();
		Container container = getContentPane();
		JLabel jl = new JLabel("A JFrame Window", JLabel.CENTER);
		URL url = MyImageIcon.class.getResource("goku.jpg");
		Icon icon = new ImageIcon(url);
		jl.setIcon(icon);
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setOpaque(true);
		container.add(jl);
		setSize(400, 300);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MyImageIcon();
	}

}
