package servlet;

import lombok.SneakyThrows;
import manager.BookManager;
import model.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/books")
public class BookServlet extends HttpServlet {

    private BookManager bookManager = new BookManager();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Book> bookList = bookManager.getAllBooks();

        req.setAttribute("books", bookList);

        req.getRequestDispatcher("/WEB-INF/books.jsp").forward(req, resp);


    }
}
