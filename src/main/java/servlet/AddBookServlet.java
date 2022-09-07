package servlet;

import manager.AuthorManager;
import manager.BookManager;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/books/add")
public class AddBookServlet extends HttpServlet {

    private BookManager bookManager = new BookManager();
    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("authors", authorManager.getAllAuthors());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/WEB-INF/addBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int authorId = Integer.parseInt(req.getParameter("author_id"));


        Book book = Book.builder()
                .title(title)
                .description(description)
                .price(price)
                .author(authorManager.getAuthorById(authorId))
                .build();
        try {
            bookManager.addBook(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/books");
    }
}
