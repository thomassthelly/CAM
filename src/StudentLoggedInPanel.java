import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;


public class StudentLoggedInPanel extends JPanel {
	public StudentLoggedInPanel(int stud_id) {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.
		add(new StudentLoggedInSidePanel(stud_id), BorderLayout.WEST);
		add(new StudentLoggedInMainPanel(stud_id), BorderLayout.CENTER);
	}
}
