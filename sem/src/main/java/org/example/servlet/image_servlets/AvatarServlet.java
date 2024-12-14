package org.example.servlet.image_servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.service.FileService;
import org.example.service.UserService;
import org.example.util.ExcProcessing;
import org.springframework.dao.EmptyResultDataAccessException;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/avatar")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10)
public class AvatarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileService fileService = (FileService) getServletContext().getAttribute("fileService");
        UserService userService = (UserService) getServletContext().getAttribute("userService");

        if (ExcProcessing.isNumber(req.getParameter("id"))) {
            Long id = Long.valueOf(req.getParameter("id"));
            try {
                fileService.downloadFile(UUID.fromString(userService.getAvatar(id)), resp);
            } catch (EmptyResultDataAccessException e) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FileService fileService = (FileService) getServletContext().getAttribute("fileService");
            UserService userService = (UserService) getServletContext().getAttribute("userService");
            Long id = (Long) req.getSession().getAttribute("userId");
            Part filePart = req.getPart("file");
            if (filePart != null && filePart.getSize() > 0) {
                UUID uploadedFileId = fileService.uploadFile(filePart);
                userService.changeAvatar(id, String.valueOf(uploadedFileId));
                resp.sendRedirect(req.getContextPath() + "/profile");
            } else {
                getServletContext().log("No file uploaded");
                resp.sendRedirect(req.getContextPath() + "/profile");
            }

        } catch (IOException e) {
            getServletContext().log("Error uploading file: " + e.getMessage(), e);
            resp.getWriter().println("Error uploading file.");
        }
    }
}
