import java.awt.CardLayout;

import javax.swing.JPanel;

public class StudentLoggedInMainPanel extends JPanel {

	CardLayout cl;

	public StudentLoggedInMainPanel(int stud_id) {
		cl = new CardLayout(0, 0);
		setLayout(cl);

		StudentLoggedInMainStatusPanel studentLoggedInMainStatusPanel = new StudentLoggedInMainStatusPanel(
				stud_id);
		add(studentLoggedInMainStatusPanel,
				"student_logged_in_main_status_panel");

		StudentLoggedInMainSubjectWisePanel studentLoggedInMainSubjectWisePanel = new StudentLoggedInMainSubjectWisePanel();
		add(studentLoggedInMainSubjectWisePanel,
				"student_logged_in_main_subject_wise_panel");

		StudentLoggedInMainReportPanel studentLoggedInMainReportPanel = new StudentLoggedInMainReportPanel();
		add(studentLoggedInMainReportPanel,
				"student_logged_in_main_report_panel");
	}
}
