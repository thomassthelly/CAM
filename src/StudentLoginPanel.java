import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StudentLoginPanel extends Panel {

	DBHelper db = null;
	ResultSet rs = null;

	public StudentLoginPanel(final JPanel p_student, final Rectangle rectangle) {
		// initialize database connection
		db = new DBHelper();
//		setLayout(new AbsoluteLayout());
		setSize(800, 600);
		setLayout(null);
//		setBounds(rectangle);
		
		
		JLabel lblYearOfJoining = new JLabel("Year of joining");
		lblYearOfJoining.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYearOfJoining.setBounds(103, 22, 114, 26);
		add(lblYearOfJoining);

		final JComboBox cb_year = new JComboBox();
		cb_year.setBounds(227, 21, 117, 26);
		add(cb_year);

		JLabel label_2 = new JLabel("Branch");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(103, 76, 74, 26);
		add(label_2);

		final JComboBox cb_branch = new JComboBox();
		cb_branch.setBounds(227, 78, 117, 26);
		add(cb_branch);

		JLabel label_1 = new JLabel("Semester");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(103, 126, 74, 26);
		add(label_1);

		final JComboBox cb_semester = new JComboBox();
		cb_semester.setBounds(227, 128, 117, 26);
		add(cb_semester);

		JLabel label_3 = new JLabel("Roll No");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(103, 181, 74, 26);
		add(label_3);

		final JTextField tf_roll_no = new JTextField();
		tf_roll_no.setBounds(0, 0, 0, 0);
		tf_roll_no.setBounds(227, 183, 117, 26);
		tf_roll_no.setColumns(10);
		add(tf_roll_no);

		JButton bView = new JButton("View");
		bView.setBounds(0, 0, 0, 0);
		bView.setBounds(227, 239, 117, 26);
		bView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String rollno = tf_roll_no.getText().toString();
				String branch = cb_branch.getSelectedItem().toString();
				String year = cb_year.getSelectedItem().toString();
				String semester = cb_semester.getSelectedItem().toString();
				if (!(rollno.isEmpty() || branch.isEmpty() || year.isEmpty() || semester
						.isEmpty())) {
					String query = "SELECT student_id FROM student WHERE roll_no = "
							+ rollno
							+ " AND batch_id = (SELECT batch_id FROM batch WHERE year_of_joining = "
							+ year
							+ " AND branch = '"
							+ branch
							+ "' AND semester = " + semester + ");";
					rs = db.query(query);
					try {
						if (!rs.next()) {
							// student not found
							JOptionPane.showMessageDialog(null,
									"Cannot be found");
						} else {
							int stud_id = Integer.parseInt(rs.getObject(1)
									.toString());
							p_student.removeAll();
							p_student.add(new StudentLoggedInPanel(stud_id,
									p_student, rectangle),
									"student_logged_in_panel");

						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"All fields are required");
				}
			}
		});
		add(bView);

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
