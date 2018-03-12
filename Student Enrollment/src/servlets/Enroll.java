package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/Enroll")
@MultipartConfig
public class Enroll extends HttpServlet {
	@Override 
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		RequestDispatcher view = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "exam", "exam");
            con.setAutoCommit(false);
            
            String roll = request.getParameter("roll");
            String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String dept = request.getParameter("dept");
			String mobile = request.getParameter("mobile");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			System.out.println(roll);
			System.out.println(fname);
			System.out.println(lname);
			System.out.println(dept);
			System.out.println(mobile);
			System.out.println(sex);
			System.out.println(email);
			System.out.println(request.getParameter("month"));
			int month = Integer.parseInt(request.getParameter("month"));
			int day = Integer.parseInt(request.getParameter("day"));
			int year = Integer.parseInt(request.getParameter("year"));
			
			
			
			String query = "INSERT INTO STUDENTS VALUES(? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, roll);
			ps.setString(2,fname);
			ps.setString(3, lname);
			ps.setString(4, dept);
			ps.setString(5, mobile);
			ps.setString(6, sex);
			ps.setString(7, email);
			ps.setInt(8, month);
			ps.setInt(9, day);
			ps.setInt(10, year);
			
//			File Part...
			InputStream inputstream = null;
			Part filePart = request.getPart("photo");
			if(filePart != null) {
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());
				
//				obtain InputStream of the uploaded File
				inputstream = filePart.getInputStream();
				
			}
			ps.setBlob(11, inputstream );
			ps.executeUpdate();
			con.commit();
			
			System.out.println("Success");
			pw.println("Success");
		}catch(Exception e ) {
			view = request.getRequestDispatcher("/studentEnrolled.html");
			view.forward(request, response);
		}
	}
}
