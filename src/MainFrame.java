import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	JLabel lblName, lblBranch, lblSemester, lblYear, lblAtt;

	public MainFrame() {
		setTitle("Attendance Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		final JPanel p_student = new JPanel();
		tabbedPane.addTab("Student", null, p_student, null);
		p_student.setLayout(new GridLayout(0, 1, 0, 0));
		p_student.add(new StudentLoginPanel(p_student, getBounds()));

		final JPanel p_teacher = new JPanel();
		tabbedPane.addTab("Teacher", null, p_teacher, null);
		p_teacher.setLayout(new GridLayout(0, 1, 0, 0));
		p_teacher.add(new TeacherLoginPanel(p_teacher, getBounds()), "teacher_login");

		
//		setSize(800, 600);
		pack();
		setLocationRelativeTo(null);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
}
