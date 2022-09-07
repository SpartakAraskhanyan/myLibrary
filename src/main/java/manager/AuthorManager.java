package manager;

import db.DBConnectionProvider;
import model.Author;
import model.Book;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class AuthorManager {
    private Connection connection;

    public AuthorManager() {

        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addAuthor(Author author) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("Insert into author(name, surname, email, age) Values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, author.getName());
        preparedStatement.setString(2, author.getSurname());
        preparedStatement.setString(3, author.getEmail());
        preparedStatement.setInt(4, author.getAge());

        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            author.setId(id);
        }

    }

    public List<Author> getAllAuthors() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM author");
        List<Author> authors = new LinkedList<>();

        while (resultSet.next()) {
            Author author = new Author();
            author.setId(resultSet.getInt("id"));
            author.setName(resultSet.getString("name"));
            author.setSurname(resultSet.getString("surname"));
            author.setEmail(resultSet.getString("email"));
            author.setAge(resultSet.getInt("age"));

            authors.add(author);
        }
        return authors;
    }

    public Author getAuthorById(int id) {
        String sql = "select * from author where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return Author.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .age(resultSet.getInt("age"))
                .build();
    }


    public void deleteAuthorById(int id) {
        String sql = "delete from author where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateAuthor(Author author) throws SQLException {
        String sql = "update author set name = ?, surname = ?, email = ?, age = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, author.getName());
        preparedStatement.setString(2, author.getSurname());
        preparedStatement.setString(3, author.getEmail());
        preparedStatement.setInt(4, author.getAge());
        preparedStatement.setInt(5,author.getId());
        preparedStatement.executeUpdate();
    }
}
