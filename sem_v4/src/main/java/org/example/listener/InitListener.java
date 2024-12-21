package org.example.listener;

import com.mongodb.MongoClient;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.mapper.impl.AnswerMapperImpl;
import org.example.mapper.impl.CommentMapperImpl;
import org.example.mapper.impl.TopicMapperImpl;
import org.example.mapper.impl.UserMapperImpl;
import org.example.repository.MongoFileRepository;
import org.example.repository.impl.*;
import org.example.service.FileService;
import org.example.service.impl.*;
import org.example.util.PropertyReader;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSource dataSource = dataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        ServletContext context = sce.getServletContext();

        context.setAttribute("jdbcTemplate", jdbcTemplate);
        context.setAttribute("dataSource", dataSource);
        context.setAttribute("topicService", new TopicServiceImpl(new TopicRepositoryImpl(jdbcTemplate), new TopicMapperImpl()));
        context.setAttribute("commentService", new CommentServiceImpl(new CommentRepositoryImpl(jdbcTemplate), new CommentMapperImpl()));
        context.setAttribute("answerService", new AnswerServiceImpl(new AnswerRepositoryImpl(jdbcTemplate), new AnswerMapperImpl()));
        context.setAttribute("likeService", new LikeServiceImpl(new LikeRepositoryImpl(jdbcTemplate)));
        context.setAttribute("userService", new UserServiceImpl(new UserRepositoryImpl(jdbcTemplate), new UserMapperImpl()));

        String defaultImageName = PropertyReader.getProperty("DEFAULT_AVATAR_NAME");

        MongoClient mongoClient = mongoClient();
        context.setAttribute("mongoClient", mongoClient);

        MongoFileRepository mongoFileRepository = new MongoFileRepositoryImpl(mongoClient);
        FileService fileService = new FileServiceImpl(mongoFileRepository, UUID.fromString(defaultImageName));
        context.setAttribute("fileService", fileService);

        List<String> PROTECTED_URIS = List.of(PropertyReader.getProperty("PROTECTED_URIS").split(","));
        context.setAttribute("PROTECTED_URIS", PROTECTED_URIS);
        List<String> NOT_AUTH_URIS = List.of(PropertyReader.getProperty("NOT_AUTH_URIS").split(","));
        context.setAttribute("NOT_AUTH_URIS", NOT_AUTH_URIS);

        String PROTECTED_REDIRECT = PropertyReader.getProperty("PROTECTED_REDIRECT");
        context.setAttribute("PROTECTED_REDIRECT", PROTECTED_REDIRECT);
        String NOT_AUTH_REDIRECT = PropertyReader.getProperty("NOT_AUTH_REDIRECT");
        context.setAttribute("NOT_AUTH_REDIRECT", NOT_AUTH_REDIRECT);

        String AUTHORIZATION = PropertyReader.getProperty("AUTHORIZATION");
        context.setAttribute("AUTHORIZATION", AUTHORIZATION);
    }

    private MongoClient mongoClient() {
        String host = PropertyReader.getProperty("MONGO_HOST");
        int port = Integer.parseInt(PropertyReader.getProperty("MONGO_PORT"));
        return new MongoClient(host, port);
    }

    private DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(PropertyReader.getProperty("DB_URL"));
        dataSource.setUser(PropertyReader.getProperty("DB_USER"));
        dataSource.setPassword(PropertyReader.getProperty("DB_PASSWORD"));
        return dataSource;
    }
}
