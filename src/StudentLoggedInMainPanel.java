import java.awt.CardLayout;
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

public class StudentLoggedInMainPanel extends JPanel {

	DBHelper db = null;
	ResultSet rs = null;

	public StudentLoggedInMainPanel(int stud_id) {
		// initialize database connection
		db = new DBHelper();

		// initialize all sub components
		// ----------------------------------
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NONE;
		
		
		JLabel lblAtt = new JLabel("");
		lblAtt.setFont(new Font("Tahoma", Font.PLAIN, 36));
		gbc.gridx = 0;
		gbc.gridy = 1;
//		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.CENTER;
		add(lblAtt, gbc);

		// initialize data into view elements
		// -------------------------------------------

		// get attendance percentage
		String att = (int) Math.floor(db.getStudentPerc(stud_id)) + "%";
		lblAtt.setText(att);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				p_student.removeAll();
//				p_student.add(new StudentLoginPanel(p_student));
				CardLayout c = (CardLayout) getParent().getLayout();
				c.previous(getParent());
			}
		});
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(btnBack, gbc);
	}
}
