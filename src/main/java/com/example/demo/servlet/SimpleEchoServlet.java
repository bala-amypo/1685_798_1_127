package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SimpleEchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");

        String name = req.getParameter("name");
        String response;

        if (name == null || name.trim().isEmpty()) {
            response = "Hello, Guest";
        } else {
            response = "Hello, " + name.trim();
        }

        PrintWriter writer = resp.getWriter();
        writer.write(response);
        writer.flush();
    }
}
