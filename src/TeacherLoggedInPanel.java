import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class TeacherLoggedInPanel extends JPanel {

	DBHelper db = null;
	ResultSet rs = null;

	public TeacherLoggedInPanel(int user_id, Rectangle rectangle) {
		db = new DBHelper();

		setLayout(null);
		setBounds(rectangle);
		JPanel p_sidebar = new JPanel();
		p_sidebar.setBounds(0, 0, 118, 300);
		add(p_sidebar);
		p_sidebar.setBackground(UIManager.getColor("Panel.background"));
		p_sidebar.setLayout(null);

		JLabel lblLoggedIn = new JLabel("Logged in as");
		lblLoggedIn.setBounds(10, 11, 78, 14);
		p_sidebar.add(lblLoggedIn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(4, 45, 110, 249);
		p_sidebar.add(panel);
		panel.setLayout(null);

		final JToggleButton tglbtnUpdate = new JToggleButton("Update");
		tglbtnUpdate.setBounds(6, 18, 98, 23);
		panel.add(tglbtnUpdate);
		tglbtnUpdate.setSelected(true);

		JToggleButton tglbtnPercentage = new JToggleButton("Percentage");
		tglbtnPercentage.setBounds(6, 52, 98, 23);
		panel.add(tglbtnPercentage);

		JToggleButton tglbtnShortage = new JToggleButton("Shortage");
		tglbtnShortage.setBounds(6, 86, 98, 23);
		panel.add(tglbtnShortage);

		JToggleButton tglbtnAddAdmin = new JToggleButton("Add Admin");
		tglbtnAddAdmin.setBounds(6, 120, 98, 23);
		panel.add(tglbtnAddAdmin);

		JToggleButton tglbtnLogout = new JToggleButton("Logout");
		tglbtnLogout.setBounds(6, 154, 98, 23);
		panel.add(tglbtnLogout);

		JLabel lblUsername = new JLabel("Imma Be");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblUsername.setBounds(10, 24, 98, 14);
		p_sidebar.add(lblUsername);

		JPanel p_main = new JPanel();
		p_main.setBounds(128, 0, 322, 300);
		add(p_main);
		p_main.setBackground(UIManager.getColor("Panel.background"));
		p_main.setLayout(new GridLayout());

		p_main.add(new TeacherUpdatePane(rectangle, JTabbedPane.TOP),
				"teacher_update_pane");

		// get username
		String sql = "SELECT username FROM user WHERE user_id = " + user_id;
		lblUsername.setText(db.getString(sql, 1));

	}

}
