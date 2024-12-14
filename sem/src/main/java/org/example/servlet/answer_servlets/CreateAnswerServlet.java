package org.example.servlet.answer_servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.request.CreateAnswerRequest;
import org.example.dto.response.OnCreateAnswerResponse;
import org.example.service.AnswerService;

import java.io.IOException;
@WebServlet("/createAnswer")
public class CreateAnswerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramId = req.getParameter("id");
        String paramId1 = req.getParameter("ids");
        CreateAnswerRequest answerRequest = CreateAnswerRequest.builder()
                .authorNick((String) req.getSession().getAttribute("userName"))
                .text(req.getParameter("answer"))
                .comment_id(paramId1)
                .build();
        AnswerService answerService = (AnswerService) getServletContext().getAttribute("answerService");
        OnCreateAnswerResponse answerResponse = answerService.createAnswer(answerRequest);
        if(answerResponse.getStatus() == 0) {
            resp.sendRedirect(req.getContextPath() + "/topic?id=" + paramId);
        } else {
            resp.sendRedirect(req.getContextPath() + "/error?err=" + answerResponse.getStatusDesc());
        }
    }
}
