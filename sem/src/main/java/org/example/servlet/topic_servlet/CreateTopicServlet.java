package org.example.servlet.topic_servlet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.request.CreateTopicRequest;
import org.example.dto.response.OnCreateTopicResponse;
import org.example.service.TopicService;

import java.io.IOException;

@WebServlet("/createTopic")
public class CreateTopicServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher =
        req.getRequestDispatcher("jsp/topics.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateTopicRequest topicRequest = CreateTopicRequest.builder()
                .nickname((String) req.getSession().getAttribute("userName"))
                .title(req.getParameter("title"))
                .description(req.getParameter("description"))
                .build();
        TopicService topicService = (TopicService) getServletContext().getAttribute("topicService");
        OnCreateTopicResponse topicResponse = topicService.createTopic(topicRequest);
        if(topicResponse.getStatus() == 0) {
            resp.sendRedirect(req.getContextPath() + "/main");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error?err=" + topicResponse.getStatusDesc());
        }
    }
}