package DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Loginn")
public class Loginn extends HttpServlet {
	public static boolean display = false;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			String username = null;
		String name=request.getParameter("email");
		System.out.println(name);
		String password=request.getParameter("Password");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/builders","root","root");
		PreparedStatement st = con.prepareStatement("select * from login where uname=? and pass=?");
		 
		
		st.setString(1, name);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			username = rs.getString("user_Name");
			display = true;
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("password", password);
			
		 response.sendRedirect("index.html");
		}
		else {
			out.println("<script type=\"text/javascript\">");
			   out.println("alert('UserName and Password incorrect');");
			   out.println("location='login.html';");
			   out.println("</script>");
	}} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	}}
