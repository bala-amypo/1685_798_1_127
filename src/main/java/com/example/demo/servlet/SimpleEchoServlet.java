package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

public class SimpleEchoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Content type REQUIRED by test
        response.setContentType("text/plain");

        String name = request.getParameter("name");

        // ✅ Handle null, empty, blank, trimmed cases
        if (name == null || name.trim().isEmpty()) {
            name = "Guest";
        } else {
            name = name.trim();
        }

        // ✅ Write response body
        PrintWriter out = response.getWriter();
        out.write("Hello, " + name);
        out.flush();
    }
}
