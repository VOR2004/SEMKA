package org.example.servlet.main_servlets;

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
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        resp.setHeader("Pragma", "no-cache"); // HTTP 1.0
        resp.setDateHeader("Expires", 0); // Proxies
        req.getRequestDispatcher(req.getContextPath() + "jsp/profil.jsp").forward(req, resp);

    }
}
