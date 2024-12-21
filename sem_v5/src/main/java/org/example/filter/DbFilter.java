package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.example.util.PropertyReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebFilter("/*")
public class DbFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            Connection connection = DriverManager.getConnection(
                    PropertyReader.getProperty("DB_URL"),
                    PropertyReader.getProperty("DB_USER"),
                    PropertyReader.getProperty("DB_PASSWORD"));
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (SQLException e) {
            servletResponse.getWriter().println("Database is not available");
        }
    }
}
