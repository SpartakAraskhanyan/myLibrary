package manager;

import db.DBConnectionProvider;
import model.Author;
import model.Book;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.valueOf;


public class BookManager {
    private Connection connection;

    AuthorManager authorManager = new AuthorManager();

    public BookManager() {


        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addBook(Book book) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("Insert into book(title, description, price, author_id) Values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getDescription());
        preparedStatement.setDouble(3, book.getPrice());
        preparedStatement.setInt(4, book.getAuthor().getId());


        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            book.setId(id);
        }


    }

    public List<Book> getAllBooks() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
        List<Book> books = new LinkedList<>();

        while (resultSet.next()) {
            Book book = new Book();
            book.setId(resultSet.getInt("id"));
            book.setTitle(resultSet.getString("title"));
            book.setDescription(resultSet.getString("description"));
            book.setPrice(resultSet.getDouble("price"));
            Author author = authorManager.getAuthorById(resultSet.getInt("author_id"));
            book.setAuthor(author);
            books.add(book);

        }
        return books;
    }

    public Book getBookById(int id) {
        String sql = "select * from book where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        return Book.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .build();
    }


    public void deleteBookById(int id) {
        String sql = "delete from book where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "update book set title = ?, description = ?, price = ?, author_id = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getDescription());
        preparedStatement.setDouble(3, book.getPrice());
        preparedStatement.setInt(4, book.getAuthor().getId());
        preparedStatement.setInt(5, book.getId());

        preparedStatement.executeUpdate();


    }

}
