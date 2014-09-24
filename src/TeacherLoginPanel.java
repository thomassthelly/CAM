import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Rectangle;

public class TeacherLoginPanel extends JPanel {

	DBHelper db = null;
	ResultSet rs = null;

	public TeacherLoginPanel(final JPanel p_teacher, final Rectangle rectangle) {
		db = new DBHelper();

		setLayout(null);
		setBounds(rectangle);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(88, 69, 80, 20);
		add(lblUsername);

		final JTextField tfUserName = new JTextField();
		tfUserName.setBounds(209, 68, 108, 27);
		add(tfUserName);
		tfUserName.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(88, 107, 80, 20);
		add(lblPassword);

		final JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(209, 106, 108, 27);
		add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = tfUserName.getText().toString();
				String pass = new String(passwordField.getPassword());
				String sql = "SELECT user_id FROM user WHERE username = '"
						+ user + "' AND password = '" + pass + "';";
				int user_id = db.getInt(sql, 1);
				if (user_id != 0) {
					p_teacher.removeAll();
					p_teacher.add(new TeacherLoggedInPanel(user_id, rectangle),
							"teacher_logged_in");
				} else {
					JOptionPane.showMessageDialog(null,
							"Invalid Username/Password");
				}
			}
		});
		btnLogin.setBounds(209, 156, 108, 27);
		add(btnLogin);
	}

}
