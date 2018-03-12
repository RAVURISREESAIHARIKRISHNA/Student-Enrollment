package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException , IOException{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "exam", "exam");
            Statement st = con.createStatement();
            String roll = request.getParameter("roll");
            System.out.println(roll);
            String Query = "SELECT * FROM STUDENTS WHERE ROLL = '"+roll+"'";
            ResultSet rs = st.executeQuery(Query);
            String imgLen = "";
//            if(rs.next()) {
//            	imgLen = rs.getString(1);
//            	System.out.println(imgLen.length());
//            }
//            rs = st.executeQuery(Query);
            if(rs.next()) {
            	InputStream input = rs.getBinaryStream("PIC");
            	OutputStream output = response.getOutputStream();
            	response.setContentType("image/png");
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
