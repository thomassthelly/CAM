import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudentLoggedInPanel extends JPanel {

	DBHelper db = null;
	ResultSet rs = null;

	public StudentLoggedInPanel(int stud_id, final JPanel p_student,
			final Rectangle rectangle) {
		// initialize database connection
		db = new DBHelper();

		// initialize all sub components
		// ----------------------------------
		setLayout(null);
		setBounds(rectangle);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(26, 23, 117, 14);
		add(lblName);

		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setBounds(153, 23, 46, 14);
		add(lblBranch);

		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(228, 23, 46, 14);
		add(lblSemester);

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(325, 23, 46, 14);
		add(lblYear);

		JLabel lblAtt = new JLabel("");
		lblAtt.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblAtt.setBounds(163, 48, 188, 73);
		add(lblAtt);

		JLabel lblTableShowingDate = new JLabel(
				"Table showing date and no of hrs");
		lblTableShowingDate.setBounds(10, 198, 264, 14);
		add(lblTableShowingDate);

		JLabel lblNoOfHours = new JLabel("No of hours needed to make up to 75%");
		lblNoOfHours.setBounds(10, 180, 243, 14);
		add(lblNoOfHours);

		JLabel lblPerSubjectAttendance = new JLabel("Per subject attendance");
		lblPerSubjectAttendance.setBounds(10, 159, 154, 14);
		add(lblPerSubjectAttendance);

		// initialize data into view elements
		// --------------------------------------------

		// get student name
		String sql = "SELECT first_name FROM student where student_id = "
				+ stud_id + ";";
		lblName.setText(db.getString(sql, 1));

		// get year, semester and branch
		rs = db.getYSBResultSet(stud_id);// db.query(s1);
		try {
			rs.next();
			lblYear.setText(rs.getObject(2).toString());
			lblSemester.setText(rs.getObject(3).toString());
			lblBranch.setText(rs.getObject(4).toString());
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// get attendance percentage
		String att = (int) Math.floor(db.getStudentPerc(stud_id)) + "%";
		lblAtt.setText(att);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p_student.removeAll();
				p_student.add(new StudentLoginPanel(p_student, rectangle));
			}
		});
		btnBack.setBounds(351, 266, 89, 23);
		add(btnBack);
	}
}
