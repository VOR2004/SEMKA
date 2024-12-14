package org.example.servlet.auth_servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.response.AuthResponse;
import org.example.dto.request.SignUpRequest;
import org.example.service.UserService;

import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email(req.getParameter("email"))
                .nickname(req.getParameter("nickname"))
                .password(req.getParameter("password"))
                .build();
        AuthResponse authResponse = userService.signUp(signUpRequest);
        if(authResponse.getStatus() == 0) {
            resp.sendRedirect(req.getContextPath() + "/signIn");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error?err=" + authResponse.getStatusDesc());
        }
    }
}
