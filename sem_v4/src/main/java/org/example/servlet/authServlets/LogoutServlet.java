package org.example.servlet.authServlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.filter.AuthFilter;

import java.io.IOException;

@WebServlet("/logout")

public class LogoutServlet extends HttpServlet {
    private String AUTHORIZATION;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        AUTHORIZATION = (String) context.getAttribute("AUTHORIZATION");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setAttribute(AUTHORIZATION, false);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
