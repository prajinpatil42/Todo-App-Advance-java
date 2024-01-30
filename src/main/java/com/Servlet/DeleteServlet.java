package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.TodoDAO;
import com.db.DBConnect;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id = Integer.parseInt(req.getParameter("id"));
		
		TodoDAO dao=new TodoDAO(DBConnect.getConn());
		
		boolean f=dao.deleteTodo(id);
		
		HttpSession session=req.getSession();
		
		if(f) {
			session.setAttribute("sucMsg","Todo Delete Successfully...");
			System.out.println("Data Insert Successfully...!");
			resp.sendRedirect("index.jsp");
		}else {
			
			session.setAttribute("failedMsg","Something Wrong on Server...!");
			System.out.println("Error..");
			resp.sendRedirect("index.jsp");
			
		}
	}

	

}
