import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class DBHelper {

	static Connection connection = null;
	static PreparedStatement ps = null;
	static Statement statement = null;
	static ResultSet resultSet = null;
	static int numberOfColumns = 0;

	public DBHelper() {
		// connect to database
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/AttMan", "root", "stardock");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Connection Error");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver Connection Error");
		}
	}

	PreparedStatement getPreparedStatement(String sql) throws SQLException {
		return ps = connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
	}

	void insertAbsentees(int hour, String date, String subjectName,
			String absentees) throws SQLException {
		String sql = "SELECT subject_id, batch_id FROM subject WHERE name = '"
				+ subjectName + "';";
		int subject_id = getInt(sql, 1);
		int batch_id = getInt(sql, 2);
		int period_id = 0;
		int roll_no;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		Date ob_date = null;
		try {
			ob_date = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat s = new SimpleDateFormat("yyyy/M/dd");
		String formattedDate = s.format(ob_date);

		int existing_period = getInt(
				"SELECT period_id FROM period WHERE date='"
						+ formattedDate
						+ "' AND hour="
						+ hour
						+ " AND subject_id IN (SELECT subject_id FROM subject WHERE batch_id = "
						+ batch_id + ");", 1);
		if (existing_period == 0) {
			// inserting data for new period
			getPreparedStatement("INSERT INTO period(period_id, date, hour, subject_id) values (null, ?, ?, ?)");
			ps.setString(1, formattedDate);
			ps.setInt(2, hour);
			ps.setInt(3, subject_id);
			if (ps.executeUpdate() != 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				period_id = Integer.parseInt(rs.getObject(1).toString());
				StringTokenizer st = new StringTokenizer(absentees, ",");
				while (st.hasMoreTokens()) {
					roll_no = Integer.parseInt(st.nextToken());
					int student_id = getInt(
							"SELECT student_id FROM student WHERE batch_id = "
									+ batch_id + " AND roll_no = " + roll_no
									+ ";", 1);
					String sql_final_insert = "INSERT INTO absentee(absentee_id, period_id, student_id) VALUES (null, ?, ?)";
					getPreparedStatement(sql_final_insert);
					ps.setInt(1, period_id);
					ps.setInt(2, student_id);
					if (ps.executeUpdate() == 0) {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Already exists ...");
		}
	}

	void performQuery(String s) {
		try {
			resultSet = statement.executeQuery(s);
			numberOfColumns = resultSet.getMetaData().getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Syntax Error?");
		}
	}

	ResultSet query(String s) {
		try {
			resultSet = statement.executeQuery(s);
			numberOfColumns = resultSet.getMetaData().getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Syntax Error?");
		}
		return resultSet;
	}

	void close() {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSet = null;
		}
	}

	String getString(String sql, int pos) {
		performQuery(sql);
		StringBuilder string = new StringBuilder();
		try {
			if (resultSet.next())
			string.append(resultSet.getObject(pos).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return string.toString();
	}

	int getInt(String sql, int pos) {
		performQuery(sql);
		int n = 0;
		try {
			if (resultSet.next())
				n = Integer.parseInt(resultSet.getObject(pos).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	float getfloat(String sql, int pos) {
		performQuery(sql);
		float n = 0;
		try {
			if (resultSet.next())
			n = Float.parseFloat(resultSet.getObject(pos).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	public double getStudentPerc(int stud_id) {
		// get total hours
		String sql_total_hrs = "SELECT COUNT(*) FROM PERIOD WHERE subject_id IN (SELECT subject_id FROM SUBJECT WHERE batch_id = (SELECT batch_id FROM STUDENT WHERE student_id = "
				+ stud_id + "));";
		int total_hours_class = getInt(sql_total_hrs, 1);

		// get total hours absent
		String sql_total_hours_absent = "SELECT COUNT(*) FROM ABSENTEE WHERE student_id = "
				+ stud_id + ";";
		int total_hours_absent = getInt(sql_total_hours_absent, 1);

		// get hours present
		int total_hours_present = total_hours_class - total_hours_absent;

		// calculate current percentage
		double perc = 100 * (total_hours_present / (double) total_hours_class);

		return perc;
	}

	void initComboBox(JComboBox cb, String sql) {
		resultSet = query(sql);
		ArrayList<String> strarr = new ArrayList<String>();
		try {
			while (resultSet.next())
				strarr.add(resultSet.getObject(1).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
			cb.setModel(new DefaultComboBoxModel(strarr.toArray()));
		}
	}

	public ResultSet getYSBResultSet(int stud_id) {
		String sql = "SELECT * FROM batch WHERE batch_id = (SELECT batch_id FROM student WHERE student_id = "
				+ stud_id + ");";
		performQuery(sql);
		return resultSet;
	}
}