package servlet;

import lombok.SneakyThrows;
import manager.AuthorManager;
import manager.BookManager;
import model.Author;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/books/update")
public class BookUpdateServlet extends HttpServlet {

    private BookManager bookManager = new BookManager();
    private AuthorManager authorManager = new AuthorManager();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookManager.getBookById(id);
        req.setAttribute("authors",authorManager.getAllAuthors());
        req.setAttribute("book", book);
        req.getRequestDispatcher("/WEB-INF/updateBook.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int author_id = Integer.parseInt(req.getParameter("author_id"));

        Book book = Book.builder()
                .id(id)
                .title(title)
                .description(description)
                .price(price)
                .author(authorManager.getAuthorById(author_id))
                .build();
        try {
            bookManager.updateBook(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/books");
    }
}
