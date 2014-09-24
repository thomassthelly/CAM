import java.awt.Rectangle;
import java.sql.ResultSet;

import javax.swing.JTabbedPane;

public class TeacherUpdatePane extends JTabbedPane {

	DBHelper db = null;
	ResultSet rs = null;

	public TeacherUpdatePane(Rectangle rectangle, int top) {
		super(top);
		//addTab("Attendance", new TeacherUpdateAttendancePanel(rectangle));
		/*addTab("Student", new TeacherUpdateStudentPanel(rectangle));
		addTab("Class", new TeacherUpdateClassPanel(rectangle));
		addTab("Subject", new TeacherUpdateSubjectPanel(rectangle));*/
	}
}
