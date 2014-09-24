import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class DBHelper {

	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultSet = null;
	static int numberOfColumns = 0;

	public DBHelper() {
//		connect to database
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/attendance", "root", "");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Connection Error");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver Connection Error");
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
			resultSet.next();
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
			resultSet.next();
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
			resultSet.next();
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