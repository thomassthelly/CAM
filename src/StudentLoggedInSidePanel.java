import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudentLoggedInSidePanel extends JPanel {

	DBHelper db = null;
	ResultSet rs = null;

	public StudentLoggedInSidePanel(int stud_id) {
		// initialize database connection
		db = new DBHelper();

		// initialize all sub components
		// ----------------------------------
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		// setBorder(BorderFactory.create);

		JLabel lblName = new JLabel("Name");
		add(lblName);

		JLabel lblBranch = new JLabel("Branch");
		add(lblBranch);

		JLabel lblSemester = new JLabel("Semester");
		add(lblSemester);

		JLabel lblYear = new JLabel("Year");
		add(lblYear);

		JPanel student_options = new JPanel();

		student_options.setLayout(new BoxLayout(student_options,
				BoxLayout.PAGE_AXIS));

		JButton status = new JButton("Status");
		status.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) getParent().getLayout();
				cl.show(getParent().getParent(),
						"student_logged_in_main_status_panel");
			}
		});
		student_options.add(status);

		JButton subject_wise = new JButton("Subject Wise");
		subject_wise.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) getParent().
						.getLayout();
				cl.show(getParent().,
						"student_logged_in_main_subject_wise_panel");
			}
		});
		student_options.add(subject_wise);

		JButton report = new JButton("Report");

		student_options.add(report);
		add(student_options);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout c = (CardLayout) getParent().getParent().getLayout();
				c.previous(getParent().getParent());
			}
		});
		student_options.add(btnBack);

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
