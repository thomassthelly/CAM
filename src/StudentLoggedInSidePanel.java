import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class StudentLoggedInSidePanel extends JPanel {

	DBHelper db = null;
	ResultSet rs = null;

	public StudentLoggedInSidePanel(int stud_id) {
		// initialize database connection
		db = new DBHelper();

		// initialize all sub components
		// ----------------------------------
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.create);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NONE;

		JLabel lblName = new JLabel("Name");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(lblName, gbc);

		JLabel lblBranch = new JLabel("Branch");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(lblBranch, gbc);

		JLabel lblSemester = new JLabel("Semester");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(lblSemester, gbc);

		JLabel lblYear = new JLabel("Year");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(lblYear, gbc);

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
	}
}
