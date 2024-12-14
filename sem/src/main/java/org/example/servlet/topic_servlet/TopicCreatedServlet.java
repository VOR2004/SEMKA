package org.example.servlet.topic_servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.CommentService;
import org.example.service.LikeService;
import org.example.service.TopicService;
import org.example.util.ExcProcessing;
import org.example.util.ReturnObjUtils;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/topic")
public class TopicCreatedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TopicService topicService = (TopicService) getServletContext().getAttribute("topicService");
        CommentService commentService = (CommentService) getServletContext().getAttribute("commentService");
        LikeService likeService = (LikeService) getServletContext().getAttribute("likeService");

        String idParTopic = req.getParameter("id");
        Long ids = (Long) req.getSession().getAttribute("userId");

        long idTopic;
        if (ExcProcessing.isNumber(idParTopic)) {
            idTopic = Long.parseLong(idParTopic);
        } else {
            idTopic = -1L;
        }

        req.setAttribute("likeCountAll", likeService.getLikesCountUser(ids));
        req.setAttribute("topicOpenedBy", req.getSession().getAttribute("userName"));

        if (topicService.getTopic(idTopic).isPresent()) {
            req.setAttribute("topicInfo", topicService.getTopic(idTopic).get());
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        req.setAttribute("listComments", ReturnObjUtils.listComment(commentService, idTopic));

        req.getRequestDispatcher("jsp/topic.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        TopicService topicService = (TopicService) getServletContext().getAttribute("topicService");
        topicService.closeOpen(id);
        resp.sendRedirect(req.getContextPath() + "/topic?id=" + id);
    }
}
