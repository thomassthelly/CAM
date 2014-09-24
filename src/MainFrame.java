import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	JLabel lblName, lblBranch, lblSemester, lblYear, lblAtt;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		setTitle("Attendance Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
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

	}
}
