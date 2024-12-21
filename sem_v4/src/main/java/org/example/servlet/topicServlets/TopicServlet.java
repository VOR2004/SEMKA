package org.example.servlet.topicServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.TopicService;
import org.example.util.ReturnObjUtils;

import java.io.IOException;



@WebServlet("/main")
public class TopicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = (String) req.getSession().getAttribute("userName");
        req.setAttribute("userName", userName);
        TopicService topicService = (TopicService) getServletContext().getAttribute("topicService");
        req.setAttribute("listTopic", ReturnObjUtils.listTopic(topicService));
        req.getRequestDispatcher("jsp/topics.jsp").forward(req, resp);
    }
}
