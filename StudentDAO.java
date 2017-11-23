package MyFirstWebApps;

import java.sql.*;
import java.util.ArrayList;


public class StudentDAO {
	private final String username;
	private final String password;
	private final String databaseURL;

	public StudentDAO() throws Exception {
		username = ConnectionParameters.username;
		password = ConnectionParameters.password;
		databaseURL = ConnectionParameters.databaseURL;

		// In *Tomcat 8* the JDBC driver must be explicitly loaded as below
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Opens a new database connection
	 * 
	 * @throws SQLException
	 */
	private Connection openConnection() throws SQLException {
		Connection dbConnection = DriverManager.getConnection(databaseURL, username, password);
		return dbConnection;
	}

	/**
	 * Closes an existing database connection
	 * 
	 * @throws SQLException
	 */
	private void closeConnection(Connection dbConnection) throws SQLException {
		if (dbConnection != null) {
			dbConnection.close();
		}
	}

	/**
	 * Retrieves all students from the database.
	 * 
	 * @return ArrayList<Student> - a List of Students
	 * @throws SQLException
	 */
	public ArrayList<Student> getAllStudents() throws SQLException {
		ArrayList<Student> studentList = new ArrayList<Student>();
		Connection dbConnection = null;

		try {
			dbConnection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice "
					+ "FROM Student ORDER BY id DESC";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");

				studentList.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));
			}

		} catch (SQLException sqle) {
			throw sqle; // Let the caller decide what to do with the exception

		} finally {
			closeConnection(dbConnection);
		}
		return studentList;
	}

	public Student getStudentById(int studentId) throws SQLException {
		Connection dbConnection = null;

		try {
			dbConnection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice "
					+ "FROM Student WHERE id = ?";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);

			ResultSet resultSet = preparedStatement.executeQuery();
			Student student1 = null;
			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");

				student1 = new Student(id, firstname, lastname, streetaddress, postcode, postoffice);

			}

			return student1;
		} catch (SQLException sqle) {
			throw sqle; // Let the caller decide what to do with the exception

		} finally {
			closeConnection(dbConnection);
		}
	}

	public int insertStudent(int studentId, String fname, String lname, String streetadd, String postcode,
			String postoffice) throws SQLException {
		Connection dbConnection = null;
		int result;
		try {
			dbConnection = openConnection();

			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);
			preparedStatement.setString(2, fname);
			preparedStatement.setString(3, lname);
			preparedStatement.setString(4, streetadd);
			preparedStatement.setString(5, postcode);
			preparedStatement.setString(6, postoffice);

			preparedStatement.executeUpdate();
			result = 0;

		} catch (SQLException sqle) {
			// First, check if the problem is primary key violation (the error
			// code is vendor-dependent)
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				result = 1;
			} else {
				result = -1;
				throw sqle; // Let the caller decide what to do with the exception
			}

		} finally {
			closeConnection(dbConnection);
		}
		return result;
	}

	public String getAllStudentsJSON() throws SQLException {
		String studentListJSON = "{\"students\":[";
		Connection dbConnection = null;

		try {
			dbConnection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice "
					+ "FROM Student ORDER BY lastname";

			PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlText);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");

				studentListJSON += "{\"id\":" + id + ", \"first name\":\"" + firstname + "\", \"last name\":\""
						+ lastname + "\", \"street address\":\"" + streetaddress + "\", \"post code\":\"" + postcode
						+ "\", \"post office\":\"" + postoffice + "\"}";
				if(!resultSet.isLast()) {
					studentListJSON += ", ";
				}
			}
			studentListJSON += "]}";

		} catch (SQLException sqle) {
			throw sqle; // Let the caller decide what to do with the exception

		} finally {
			closeConnection(dbConnection);
		}
		return studentListJSON;
	}
}