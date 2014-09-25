import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TeacherLoggedInPanel extends JPanel {

	private DBHelper db;

	public TeacherLoggedInPanel() {
		db = new DBHelper();
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.NONE;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel lblYearOfJoining = new JLabel("Year of joining");
		lblYearOfJoining.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblYearOfJoining, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		final JComboBox cb_year = new JComboBox();
		add(cb_year, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel label_2 = new JLabel("Branch");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(label_2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		final JComboBox cb_branch = new JComboBox();
		add(cb_branch, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel label_1 = new JLabel("Semester");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(label_1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		final JComboBox cb_semester = new JComboBox();
		add(cb_semester, gbc);

		// add data to the subject combo box
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel lbl_subject = new JLabel("Subject");
		lbl_subject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_subject, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		final JComboBox cb_subject = new JComboBox();
		add(cb_subject, gbc);
		
		// setting up combo boxes
		// -------------------------

		// get years from the database
		String sql_year_of_joining = "SELECT DISTINCT(year_of_joining) as year FROM batch ORDER BY year";
		db.initComboBox(cb_year, sql_year_of_joining);

		// get semesters from the database
		String sql_semester = "SELECT DISTINCT(semester) as sem FROM batch ORDER BY sem";
		db.initComboBox(cb_semester, sql_semester);

		// get branches from the database
		String sql_branch = "SELECT DISTINCT(branch) FROM batch";
		db.initComboBox(cb_branch, sql_branch);
	}
}
