package org.example.servlet.authServlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.dto.response.AuthResponse;
import org.example.dto.request.SignInRequest;
import org.example.filter.AuthFilter;
import org.example.service.UserService;

import java.io.IOException;

@WebServlet("/signIn")

public class SignInServlet extends HttpServlet {
    private String AUTHORIZATION;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        SignInRequest signInRequest = SignInRequest.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();
        AuthResponse authResponse = userService.signIn(signInRequest);
        if(authResponse.getStatus() == 0) {
            HttpSession session = req.getSession(true);
            session.setAttribute(AUTHORIZATION, true);
            session.setAttribute("userName", authResponse.getUser().getNickname());
            session.setAttribute("userId", authResponse.getUser().getId());
            resp.sendRedirect(req.getContextPath() + "/main");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error?err=" + authResponse.getStatusDesc());
        }
    }
    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        AUTHORIZATION = (String) context.getAttribute("AUTHORIZATION");
    }
}
