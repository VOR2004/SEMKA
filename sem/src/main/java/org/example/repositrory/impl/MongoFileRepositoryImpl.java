package org.example.repositrory.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.Binary;
import org.example.exceptions.FileNotFoundExc;
import org.example.repositrory.MongoFileRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j

public class MongoFileRepositoryImpl implements MongoFileRepository {
    private final MongoCollection<Document> mongoCollection;
    public MongoFileRepositoryImpl(MongoClient client) {
        mongoCollection = client.getDatabase("siteDBMongo").getCollection("files");
    }

    @Override
    public UUID saveFile(InputStream inputStream) throws IOException {
        UUID id = UUID.randomUUID();
        Binary data = new Binary(inputStream.readAllBytes());
        Document document = new Document("file", data)
                .append("id", id.toString());
        mongoCollection.insertOne(document);
        return id;
    }

    @Override
    public byte[] getFile(UUID id) {
        Document selector = new Document("id", id.toString());
        Document result = mongoCollection.find(selector).first();
        if (result != null) {
            return result.get("file", Binary.class).getData();
        }
        throw new FileNotFoundExc("Not found");
    }
}
