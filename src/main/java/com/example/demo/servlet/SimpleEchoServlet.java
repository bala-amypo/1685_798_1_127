package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/ping", "/health"})
public class SimpleEchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        String uri = req.getRequestURI();

        if (uri.endsWith("/ping")) {
            resp.getWriter().write("PONG");
        } else if (uri.endsWith("/health")) {
            resp.getWriter().write("OK");
        }
    }
}
