package org.example.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.FileNotFoundExc;
import org.example.repository.MongoFileRepository;
import org.example.service.FileService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final MongoFileRepository mongoFileRepository;

    private final UUID defaultImageUuid;


    @Override
    public UUID uploadFile(Part part) throws IOException {
        return mongoFileRepository.saveFile(part.getInputStream());
    }


    @Override
    public void downloadFile(UUID id, HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();
        try {
            byte[] data = mongoFileRepository.getFile(id);
            os.write(data);
        } catch (FileNotFoundExc e) {
            byte[] data = mongoFileRepository.getFile(defaultImageUuid);
            os.write(data);
        }
    }
}
