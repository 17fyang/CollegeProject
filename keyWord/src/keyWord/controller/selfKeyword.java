package keyWord.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

@WebServlet("/selfKeyword")
public class selfKeyword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public selfKeyword() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html;charset=utf-8");
		String keyword=request.getParameter("keyword");
		CMD c=new CMD();
		JSONObject json=c.getJson(keyword);
		response.getWriter().write(json.toJSONString());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
