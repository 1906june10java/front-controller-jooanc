package com.revature.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DispatcherServlet extends HttpServlet {

	/**
	 * Java 2.x compatiblity of serialization
	 */
	private static final long serialVersionUID = 55343653124378175L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object data = RequestHelper.process(request);
		
		if(data instanceof String) {
			String path = (String) data;
			request.getRequestDispatcher(path).forward(request, response);
		} else {
		response.getWriter()
		.write(new ObjectMapper()
				.writeValueAsString(data));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

