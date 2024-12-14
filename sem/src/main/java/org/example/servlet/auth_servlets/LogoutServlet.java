package org.example.servlet.auth_servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.filter.AuthFilter;

import java.io.IOException;

@WebServlet("/logout")

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setAttribute(AuthFilter.AUTHORIZATION, false);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
