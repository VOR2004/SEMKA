package org.example.servlet.like_servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.LikeService;

import java.io.IOException;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        LikeService likeService = (LikeService) getServletContext().getAttribute("likeService");
        likeService.leaveLike(
                Integer.valueOf(req.getSession().getAttribute("userId").toString()),
                Integer.valueOf(req.getParameter("commentId")));

        resp.setContentType("application/json");
        resp.getWriter().write("{\"message\": \"success\"}");
    }
}
