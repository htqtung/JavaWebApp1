package MyFirstWebApps;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentSearchServlet
 */
@WebServlet("/StudentSearchServlet")
public class StudentSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			StudentDAO studentDAO = new StudentDAO();
			Student studentFound = null;

			int givenID = -1;

			// 1. Retrieve the values of the request parameters
			String idText = request.getParameter("txtID");

			if (idText != null) {
				try {
					givenID = Integer.parseInt(idText);
				} catch (Exception ex) {
				}
			}

			// ID is a valid number
			if (givenID > 0) {
				studentFound = studentDAO.getStudentById(givenID);
			}

			// add the data to the request attributes
			request.setAttribute("student", studentFound);
			request.setAttribute("txtID", givenID);

			// forward the request back to the JSP page
			request.getRequestDispatcher("StudentSearch.jsp").forward(request, response);

		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

}
