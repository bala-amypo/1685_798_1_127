package com.example.demo.servlet;

import jakarta.servlet.http.*;
import java.io.IOException;

public class SimpleEchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("text/plain");
        String name = req.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            res.getWriter().write("Hello, Guest");
        } else {
            res.getWriter().write("Hello, " + name.trim());
        }
    }
}
