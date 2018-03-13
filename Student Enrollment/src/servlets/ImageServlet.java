package servlets;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

            if(rs.next()) {
            	Blob b = rs.getBlob(11);
            	byte barr[] = b.getBytes(1,(int)b.length());
            	ServletOutputStream sos = response.getOutputStream();
            	response.setContentType("image/png");
            	sos.write(barr);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
