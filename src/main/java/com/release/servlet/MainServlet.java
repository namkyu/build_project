package com.release.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @FileName : MainServlet.java
 * @Project : build_project
 * @작성자 : nklee
 * @프로그램설명 :
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * <pre>
	 * doGet
	 *
	 * <pre>
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("Test");
        response.getWriter().flush();
        response.getWriter().close();
	}

	/**
	 * <pre>
	 * service
	 *
	 * <pre>
	 * @param arg0
	 * @param arg1
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("test");
	}
}
