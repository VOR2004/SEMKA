package org.example.servlet.comment_servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.dto.request.CreateCommentRequest;
import org.example.dto.response.OnCreateCommentResponse;
import org.example.service.CommentService;
import org.example.service.FileService;

import java.io.IOException;


@WebServlet("/createComment")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10)

public class CreateCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramId = req.getParameter("id");
        Long userId = (Long) req.getSession().getAttribute("userId");
        CommentService commentService = (CommentService) getServletContext().getAttribute("commentService");

        FileService fileService = (FileService) getServletContext().getAttribute("fileService");

        Part filePart = req.getPart("file");

        String uploadedFileId;
        if (filePart != null && filePart.getSize() > 0) {
            uploadedFileId = fileService.uploadFile(filePart).toString();
        } else {
            uploadedFileId = null;
        }

        CreateCommentRequest commentRequest = CreateCommentRequest.builder()
                    .authorNick((String) req.getSession().getAttribute("userName"))
                    .user_id(Math.toIntExact(userId))
                    .text(req.getParameter("comment"))
                    .topic_id(paramId)
                    .image_id(uploadedFileId)
                    .build();

        OnCreateCommentResponse commentResponse = commentService.createComment(commentRequest);
        if (commentResponse.getStatus() == 0) {
            resp.sendRedirect(req.getContextPath() + "/topic?id=" + paramId);
        } else {
            resp.sendRedirect(req.getContextPath() + "/error?err=" + commentResponse.getStatusDesc());
        }
    }
}
