package com.kopo.library.repository;

import com.kopo.library.domain.Book;
import com.kopo.library.view.MainView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepoDbImpl implements BookRepository{

    Connection connection = MainView.connection;
    //    Book book = MainView.book;
//    List<Book> books = MainView.books;


    @Override
    public void insertBook(Book book) {
        String query = "INSERT INTO BOOK (bookId, title, author, publisher, publicationDate, isPossibleBorrow) " +
                "VALUES (BOOK_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

        String title = book.getTitle();
        String author = book.getAuthor();
        String publisher = book.getPublisher();
        String publicationDate = book.getPublicationDate();
        boolean isPossibleBorrow = book.isPossibleBorrow();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, publisher);
            preparedStatement.setString(4, publicationDate);
            preparedStatement.setString(5, Boolean.toString(isPossibleBorrow));

            preparedStatement.executeUpdate();
            System.out.println("도서 등록이 완료되었습니다.");

            connection.commit(); // COMMIT 수행


        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }

    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void deleteBook(Book book) {

    }

    @Override
    public List<Book> findAllBook() {
        List<Book> books = new ArrayList<>();

        String query = "SELECT * FROM BOOK "
                + "ORDER BY bookId";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query);)
        {
            // Query 결과를 처리
            while (resultSet.next()) {
                Long bookId = resultSet.getLong("bookId");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                String publicationDate = resultSet.getString("publicationDate");
                boolean isPossibleBorrow = resultSet.getBoolean("isPossibleBorrow");

                Book book = new Book(bookId, title, author, publisher, publicationDate, isPossibleBorrow);
                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }

        return books;

    }

    @Override
    public Book findById(Long bookId) {
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        return null;
    }

    @Override
    public Book restore(Book book) {
        return null;
    }
}
