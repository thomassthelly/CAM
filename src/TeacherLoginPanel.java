import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TeacherLoginPanel extends JPanel {

	DBHelper db = null;
	ResultSet rs = null;

	public TeacherLoginPanel(final JPanel p_teacher) {
		db = new DBHelper();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = .5;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_END;

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		// lblUsername.setBounds(88, 69, 80, 20);
		add(lblUsername, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		final JTextField tfUserName = new JTextField();
		// tfUserName.setBounds(209, 68, 108, 27);
		tfUserName.setColumns(10);
		add(tfUserName, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		// lblPassword.setBounds(88, 107, 80, 20);
		add(lblPassword, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		final JPasswordField passwordField = new JPasswordField();
		passwordField.setColumns(10);
		// passwordField.setBounds(209, 106, 108, 27);
		add(passwordField, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 1;
		gbc.weighty = 4;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
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
					p_teacher.add(new TeacherLoggedInPanel(),
							"teacher_logged_in");
				} else {
					JOptionPane.showMessageDialog(null,
							"Invalid Username/Password");
				}
			}
		});
		// btnLogin.setBounds(209, 156, 108, 27);
		add(btnLogin, gbc);
	}

}
