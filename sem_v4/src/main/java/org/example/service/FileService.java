package org.example.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.UUID;

public interface FileService {

    UUID uploadFile(Part part) throws IOException;

    void downloadFile(UUID id, HttpServletResponse response) throws IOException;

}
