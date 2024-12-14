package org.example.servlet.image_servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.FileService;
import org.springframework.dao.EmptyResultDataAccessException;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/imageComm")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10)

public class ImageCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileService fileService = (FileService) getServletContext().getAttribute("fileService");
        if (req.getParameter("id") != null) {
            String id = (req.getParameter("id"));
            try {
                fileService.downloadFile(UUID.fromString(id), resp);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
