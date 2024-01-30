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
import com.entity.TodoDetails;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id=Integer.parseInt(req.getParameter("id"));
		String username=req.getParameter("username");
		String todo=req.getParameter("todo");
		String status=req.getParameter("status");

//		System.out.println(username+" "+todo+" "+status);
		
		TodoDAO dao=new TodoDAO(DBConnect.getConn());
		
		TodoDetails t=new TodoDetails();
		
		t.setId(id);
		t.setName(username);
		t.setTodo(todo);
		t.setStatus(status);
		
		boolean f=dao.updateTodo(t);
		HttpSession session=req.getSession();
		
		if(f) {
			session.setAttribute("sucMsg","Todo Update Successfully...");
			System.out.println("Data Insert Successfully...!");
			resp.sendRedirect("index.jsp");
		}else {
			
			session.setAttribute("failedMsg","Something Wrong on Server...!");
			System.out.println("Error..");
			resp.sendRedirect("index.jsp");
			
		}
		
		
		
	}

}
