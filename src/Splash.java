import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Splash extends JFrame {

	private JPanel contentPane;
	StringBuilder sb;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash frame = new Splash();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Splash() {

		System.setProperty("apple.laf.useScreenMenuBar", "true"); 

		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Imma Be :D");
		
		
		// initialize all sub components
		// ----------------------------------

		sb = new StringBuilder("College Attendance Manager");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().width, 234);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setBackground(new Color(34, 139, 34));
		contentPane.setBackground(new Color(34, 139, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());

		final JLabel lblNewLabel = new JLabel(sb.toString());
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 72));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.CENTER);

		getContentPane().add(contentPane);
		setUndecorated(true);
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(4);
					new MainFrame();
					setVisible(false);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		
	}
}
