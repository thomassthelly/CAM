import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TeacherLoggedInPanel extends JPanel implements ActionListener {

	private DBHelper db;
	private JComboBox cb_subject, cb_semester, cb_branch, cb_hour;

	public TeacherLoggedInPanel() {
		db = new DBHelper();

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.NONE;

		// label - year of joining
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel lblYearOfJoining = new JLabel("Year of joining");
		lblYearOfJoining.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblYearOfJoining, gbc);

		// combo box - year of joining
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		final JComboBox cb_year = new JComboBox();
		add(cb_year, gbc);

		// label - branch
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel label_2 = new JLabel("Branch");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(label_2, gbc);

		// combo box - branch
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		cb_branch = new JComboBox();
		cb_branch.addActionListener(this);
		add(cb_branch, gbc);

		// label - semester
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel label_1 = new JLabel("Semester");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(label_1, gbc);

		// combo box - semester
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		cb_semester = new JComboBox();
		cb_semester.addActionListener(this);
		add(cb_semester, gbc);

		// label - date
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel lbl_date = new JLabel("Date");
		lbl_date.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_date, gbc);

		// text field - date
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_START;

		final JTextField tf_date = new JTextField();
		tf_date.setColumns(10);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		tf_date.setText(sdf.format(new Date()));
		add(tf_date, gbc);

		// label - hour
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel lbl_hour = new JLabel("Hour");
		lbl_hour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_hour, gbc);

		// combo box - hour
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_START;
		cb_hour = new JComboBox();
		add(cb_hour, gbc);

		// label - subject
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel lbl_subject = new JLabel("Subject");
		lbl_subject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_subject, gbc);

		// combo box - subject
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.LINE_START;
		cb_subject = new JComboBox();
		add(cb_subject, gbc);

		// label - absentees
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel lbl_absentees = new JLabel("Absentees");
		lbl_absentees.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_absentees, gbc);

		// text field - absentees
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.LINE_START;

		final JTextField tf_absentees = new JTextField();
		tf_absentees.setColumns(10);
		tf_absentees
				.setToolTipText("Enter absentees roll numbers separated by commas");
		add(tf_absentees, gbc);

		// button - insert
		JButton b_insert = new JButton("Insert");
		b_insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// **********************
				// INSERT ABSENTEE BUTTON
				// **********************
				String absentees = tf_absentees.getText().toString();
				String subjectName = cb_subject.getSelectedItem().toString();
				String date = tf_date.getText().toString();
				int hour = Integer.parseInt(cb_hour.getSelectedItem()
						.toString());

				try {
					db.insertAbsentees(hour, date, subjectName, absentees);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(b_insert, gbc);

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

		// get subjects from database
		String sql_subject = "SELECT name FROM subject WHERE batch_id = (SELECT batch_id FROM batch WHERE semester = "
				+ cb_semester.getSelectedItem().toString()
				+ " AND branch = '"
				+ cb_branch.getSelectedItem().toString() + "')";
		db.initComboBox(cb_subject, sql_subject);

		// get no. of hours from database
		String sql_hours = "SELECT DISTINCT(hour) FROM period";
		db.initComboBox(cb_hour, sql_hours);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// get subjects from database
		String sql_subject = "SELECT name FROM subject WHERE batch_id = (SELECT batch_id FROM batch WHERE semester = "
				+ cb_semester.getSelectedItem().toString()
				+ " AND branch = '"
				+ cb_branch.getSelectedItem().toString() + "')";
		db.initComboBox(cb_subject, sql_subject);
	}
}
