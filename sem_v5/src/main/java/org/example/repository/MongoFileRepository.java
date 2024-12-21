package org.example.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public interface MongoFileRepository {

    UUID saveFile(InputStream inputStream) throws IOException;

    byte[] getFile(UUID id);

}
