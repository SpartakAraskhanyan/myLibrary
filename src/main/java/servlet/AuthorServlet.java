package servlet;

import lombok.SneakyThrows;
import manager.AuthorManager;
import model.Author;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/authors")
public class AuthorServlet extends HttpServlet {

    private AuthorManager authorManager = new AuthorManager();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Author> authorList = authorManager.getAllAuthors();

        req.setAttribute("authors", authorList);

        req.getRequestDispatcher("/WEB-INF/authors.jsp").forward(req, resp);


    }
}
