package MyFirstWebApps;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

/**
 * Servlet implementation class StudentListServlet
 */

@WebServlet("/StudentListServlet")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			StudentDAO studentDAO = new StudentDAO();
			
			// Create the student list
			ArrayList<Student> studentList = studentDAO.getAllStudents();
			
			// add the data to the request attributes
			request.setAttribute("studentList", studentList);
			
			// forward the request back to the JSP page
			request.getRequestDispatcher("StudentList.jsp").forward(request, response);
			
		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
