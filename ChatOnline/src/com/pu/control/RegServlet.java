package com.pu.control;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

import com.pu.service.ChatService;

@WebServlet(urlPatterns = {"/reg.do" })
// ע��Ŀ�����
public class RegServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("GBK");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		if (name == null || name.trim().equals("") || pass == null
				|| pass.trim().equals("")) {
			request.setAttribute("tip", "�û��������붼����Ϊ��");

		} else {
			try {
				// ���ע��ɹ�
				if (ChatService.instance().addUser(name, pass)) {
					request.setAttribute("tip", "ע��ɹ������¼ϵͳ");
				}

			} catch (Exception e) {
				request.setAttribute("tip", "�޷�����ע�ᣬ������");
			}

		}
		forward("/reg.jsp",request,response);
	}
//ִ��ת������ķ�����
	private void forward(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
