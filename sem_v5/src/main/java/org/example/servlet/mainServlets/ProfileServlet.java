package org.example.servlet.mainServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.LikeService;

import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LikeService likeService = (LikeService) getServletContext().getAttribute("likeService");
        req.setAttribute("likeCountAll", likeService.getLikesCountUser((Long) req.getSession().getAttribute("userId")));
        req.getRequestDispatcher("jsp/profile.jsp").forward(req, resp);
    }
}
