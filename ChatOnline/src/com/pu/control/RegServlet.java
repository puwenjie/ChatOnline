package com.pu.control;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

import com.pu.service.ChatService;

@WebServlet(urlPatterns = {"/reg.do" })
// 注册的控制器
public class RegServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("GBK");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		if (name == null || name.trim().equals("") || pass == null
				|| pass.trim().equals("")) {
			request.setAttribute("tip", "用户名和密码都不能为空");

		} else {
			try {
				// 如果注册成功
				if (ChatService.instance().addUser(name, pass)) {
					request.setAttribute("tip", "注册成功，请登录系统");
				}

			} catch (Exception e) {
				request.setAttribute("tip", "无法正常注册，请重试");
			}

		}
		forward("/reg.jsp",request,response);
	}
//执行转发请求的房方法
	private void forward(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
