import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		
		
		
		
		JLabel lblName = new JLabel("Name");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(lblName, gbc);

		JLabel lblBranch = new JLabel("Branch");
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(lblBranch, gbc);

		JLabel lblSemester = new JLabel("Semester");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(lblSemester, gbc);

		JLabel lblYear = new JLabel("Year");
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(lblYear, gbc);

		JLabel lblAtt = new JLabel("");
		lblAtt.setFont(new Font("Tahoma", Font.PLAIN, 36));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.CENTER;
		add(lblAtt, gbc);

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
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(btnBack, gbc);
	}
}
