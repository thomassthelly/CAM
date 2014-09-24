import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class TeacherUpdateAttendancePanel extends JPanel {
	public TeacherUpdateAttendancePanel(Rectangle rectangle) {
		setLayout(new GridLayout(1, 0, 0, 0));
		setBounds(rectangle);
		
		add(new StudentLoginPanel(this, rectangle));
	}
}