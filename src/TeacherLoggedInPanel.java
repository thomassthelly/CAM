import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TeacherLoggedInPanel extends JPanel {

	private DBHelper db;
	private JComboBox cb_subject;

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
		final JComboBox cb_branch = new JComboBox();
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
		final JComboBox cb_semester = new JComboBox();
		cb_semester.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// get subjects from database
				String sql_subject = "SELECT name FROM subject WHERE batch_id = (SELECT batch_id FROM batch WHERE semester = "
						+ cb_semester.getSelectedItem().toString()
						+ " AND branch = '"
						+ cb_branch.getSelectedItem().toString() + "')";
				db.initComboBox(cb_subject, sql_subject);
			}
		});
		add(cb_semester, gbc);

		// label - subject
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel lbl_subject = new JLabel("Subject");
		lbl_subject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_subject, gbc);

		// combo box - subject
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		cb_subject = new JComboBox();
		add(cb_subject, gbc);
		
		// label - date
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel lbl_date = new JLabel("Date");
		lbl_date.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lbl_date, gbc);

		// text field - date
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_START;
		
		JTextField tf_date = new JTextField();
		tf_date.setColumns(10);
		Calendar c = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(c.get(Calendar.DAY_OF_MONTH));
		sb.append("/");
		sb.append(c.get(Calendar.MONTH));
		sb.append("/");
		sb.append(c.get(Calendar.YEAR));
		tf_date.setText(sb.toString());
		add(tf_date, gbc);
		
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

	}
}
