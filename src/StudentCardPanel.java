import java.awt.CardLayout;

import javax.swing.JPanel;

public class StudentCardPanel extends JPanel {
	public StudentCardPanel() {
		CardLayout cl = new CardLayout(0, 0);
		setLayout(cl);
		
		add(new StudentLoginPanel(this), "student_login_panel");
	}
}
