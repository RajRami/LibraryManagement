package com.example.librarymanagement;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBUtility {

    private static String user = DBCredentials.user;
    private static String password = DBCredentials.password;
    private static String url = "jdbc:mysql://localhost:3306/library_management";
    private static ArrayList<Book> availableBooks = new ArrayList<>();
    private static ArrayList<Book> issuedBooks = new ArrayList<>();

    public static ArrayList<Book> getAvailableBooksFromDB() {

        String sql = "SELECT * FROM available_books;";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected...");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String bookName = resultSet.getString("bookName");
                String authorName = resultSet.getString("authorName");
                Date date = resultSet.getDate("publishedDate");
                LocalDate publishedDate = date.toLocalDate();

                availableBooks.add(new Book(bookName, authorName, publishedDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return availableBooks;
    }

    public static ArrayList<Book> getIssuedBooksFromDB() {

        String sql = "SELECT * FROM issued_books";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected...");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String bookName = resultSet.getString("bookName");
                String authorName = resultSet.getString("authorName");
                Date pDate = resultSet.getDate("publishedDate");
                String issuerName = resultSet.getString("issuerName");
                String issuerContact = resultSet.getString("issuerContact");
                Date iDate = resultSet.getDate("issuedDate");
                LocalDate publishedDate = pDate.toLocalDate();
                LocalDate issuedDate = iDate.toLocalDate();

                issuedBooks.add(new Book(bookName, authorName, publishedDate, issuerName, issuerContact, issuedDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return issuedBooks;
    }

    public static void insertNewBookIntoDB(Book book) {

        String sql = "INSERT INTO available_books(bookName, authorName, publishedDate) VALUES (?,?,?);";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected...");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            preparedStatement.setDate(3, Date.valueOf(book.getPublishedDate()));

            preparedStatement.executeUpdate();
            System.out.println("Inserted...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void issueABook(Book book) {
        String sql = "INSERT INTO issued_books(bookName, authorName, publishedDate, issuerName, issuerContact, issuedDate) VALUES (?,?,?,?,?,?);";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected...");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getAuthorName());
            preparedStatement.setDate(3, Date.valueOf(book.getPublishedDate()));
            preparedStatement.setString(4, book.getIssuerName());
            preparedStatement.setString(5, book.getIssuerContact());
            preparedStatement.setDate(6, Date.valueOf(book.getIssuedDate()));

            preparedStatement.executeUpdate();
            System.out.println("Issued...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void returnABook(Book book) {
        insertNewBookIntoDB(book);
        deleteBookFromIssuedBooks(book.getBookName(), book.getAuthorName());
    }

    public static void deleteBookFromAvailableBooks(String bookName1, String authorName1) {
        String sql = String.format("DELETE FROM available_books WHERE bookName = '%s' AND authorName = '%s';", bookName1, authorName1);

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Deleted...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteBookFromIssuedBooks(String bookName1, String authorName1) {
        String sql = String.format("DELETE FROM issued_books WHERE bookName = '%s' AND authorName = '%s';", bookName1, authorName1);

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Deleted...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
