import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

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

		// initialize all sub components
		// ----------------------------------
		sb = new StringBuilder("College Attendance Manager ");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(0, 284,
		// java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
		// 200);
		setSize(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().width, 234);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setBackground(new Color(34, 139, 34));
		contentPane.setBackground(new Color(34, 139, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// setContentPane(contentPane);
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
					// int l = sb.length();
					// System.out.println(l);
					// thread creates 3s delay
					// for (int i = 1; i < 3000; i++) {
					// TODO Uncomment Sleep Code
					Thread.sleep(4000);
					/*
					 * sb.append("."); lblNewLabel.setText(sb.toString()); //
					 * System.out.println(sb.toString()); if(i%3 == 0)
					 * sb.delete(l, l+3); }
					 */
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
