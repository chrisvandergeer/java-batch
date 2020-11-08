package nl.cge.example.javabatch.boundary;

import nl.cge.example.javabatch.control.VerwerkUploadBestandController;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UploadBestandServlet", value = "uploadBestand")
@MultipartConfig
public class UploadBestandServlet extends HttpServlet {

    @Inject
    private VerwerkUploadBestandController verwerkUploadBestandController;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Part file = req.getPart("file");
        verwerkUploadBestandController.verwerk(file.getInputStream(), file.getSubmittedFileName());
        writer.println("gelukt");
        resp.flushBuffer();
    }
}
