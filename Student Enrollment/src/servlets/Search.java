package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Search")
public class Search extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "exam", "exam");
            Statement st = con.createStatement();
            String roll = request.getParameter("roll");
            System.out.println(roll);
            String query = "SELECT FIRST_NAME,LAST_NAME,DEPT,MOBILE,SEX,EMAIL,MONTH,DAY,YEAR FROM STUDENTS WHERE ROLL = '"+roll+"'";
            System.out.println(query);
            
            ResultSet rs = st.executeQuery(query);
            System.out.println("Came here");
            PrintWriter pw = response.getWriter();
            response.setContentType("text/html");
            if(rs.next()) {
            	String fname = rs.getString("FIRST_NAME");
            	System.out.println("First Name : "+fname);
            	String lname = rs.getString("LAST_NAME");
            	System.out.println(lname);
            	String dept = rs.getString("DEPT");
            	System.out.println(dept);
            	String mobile = rs.getString("MOBILE");
            	System.out.println(mobile);
            	String sex = rs.getString("SEX");
            	System.out.println(sex);
            	String email = rs.getString("EMAIL");
            	System.out.println(email);
            	int month = rs.getInt("MONTH");
            	System.out.println(month);
            	int day = rs.getInt("DAY");
            	System.out.println(day);
            	int year = rs.getInt("YEAR");
            	System.out.println(year);
            	
            	
            	String html = "";
            	html = html + "<!DOCTYPE html>\r\n" + 
            			"<html>\r\n" + 
            			"    <head>\r\n" + 
            			"        <title>";
            	html = html + fname;
            	html = html + "</title>\r\n" + 
            			"    </head>\r\n" + 
            			"    <body>\r\n" + 
            			"        <h1></h1>\r\n" + 
            			"        <img src=\"ImageServlet?roll=";;;
            	html = html + roll;
            	html = html + "\">\r\n" + 
            			"        <p></p>\r\n" + 
            			"        <b>ROLL</b> : ";
            	html = html + roll;
            	html = html + "\r\n" + 
            			"        <p></p>\r\n" + 
            			"        <b>First Name</b> : ";
            	html = html + fname;
            	html = html + "\r\n" + 
            			"        <p></p>\r\n" + 
            			"        <b>Last Name</b> : ";
            	html = html + lname;
            	html = html + "\r\n" + 
            			"        <p></p>\r\n" + 
            			"        <b>Department</b> : ";
            	html = html + dept;
            	html = html + "\r\n" + 
            			"        <p></p>\r\n" + 
            			"        <b>Mobile</b> : ";
            	html = html + mobile;
            	html = html + "\r\n" + 
            			"        <p></p>\r\n" + 
            			"        <b>Born on</b> : ";
            	html = html +day + " of "+month+" in "+year;
            	html = html + "\r\n" + 
            			"\r\n" + 
            			"    </body>\r\n" + 
            			"</html>";
            	pw.println(html);
            }else {
            	String html = "<!DOCTYPE html>\r\n" + 
            			"<html>\r\n" + 
            			"    <head>\r\n" + 
            			"        <title>Not Found</title>\r\n" + 
            			"    </head>\r\n" + 
            			"    <body>\r\n" + 
            			"        <h1>Student Not Found</h1>";
            	html = html + " </body>\r\n" + 
            			"</html>";
            	pw.println(html);
            }
		}catch(Exception e) {
			
		}
	}
}
