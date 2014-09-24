import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class test extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(Dim.getScreenWidth()-(Dim.WIDTH/2), Dim.getScreenHeight()-(Dim.HEIGHT/2), Dim.WIDTH, Dim.HEIGHT);
		contentPane = new JPanel();
		add(contentPane);
		setSize(Dim.WIDTH, Dim.HEIGHT);
		setLocationRelativeTo(null);
	}
}
