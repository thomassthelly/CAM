import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class StudentLoggedInPanel extends JPanel {

	StudentLoggedInMainPanel studentLoggedInMainPanel;
	public StudentLoggedInPanel(int stud_id) {
		setLayout(null);
		// GridBagConstraints gbc = new GridBagConstraints();
		//
		// gbc.gridx = 0;
		// gbc.gridy = 0;
		// gbc.weightx = 1;
		// gbc.weighty = 1;
		// gbc.fill = GridBagConstraints.NONE;

		StudentLoggedInSidePanel studentLoggedInSidePanel = new StudentLoggedInSidePanel(
				stud_id);
		studentLoggedInSidePanel.setBounds(6, 5, 118, 289);
		add(studentLoggedInSidePanel);

		// JSeparator sep = new JSeparator(JSeparator.VERTICAL);
		// sep.setVisible(true);
		// gbc.gridx = 1;
		// add(sep, gbc);

		// gbc.gridx = 2;
		studentLoggedInMainPanel = new StudentLoggedInMainPanel(
				stud_id);
		studentLoggedInMainPanel.setBounds(144, 5, 300, 289);
		add(studentLoggedInMainPanel);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(129, 5, 15, 289);
		add(separator);
	}
}
