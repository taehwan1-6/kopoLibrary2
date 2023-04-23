package com.kopo.library.repository;

import com.kopo.library.domain.Book;
import com.kopo.library.domain.GenderStatus;
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
        String query = "UPDATE BOOK SET title = ?, author = ?, publisher = ?, publicationDate = ?, isPossibleBorrow = ? WHERE bookId = ?";

        String title = book.getTitle();
        String author = book.getAuthor();
        String publisher = book.getPublisher();
        String publicationDate = book.getPublicationDate();
        boolean isPossibleBorrow = book.isPossibleBorrow();
        Long bookId = book.getBookId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, publisher);
            preparedStatement.setString(4, publicationDate);
            preparedStatement.setString(5, Boolean.toString(isPossibleBorrow));
            preparedStatement.setLong(6, bookId);

            preparedStatement.executeUpdate();
            System.out.println("도서 정보 수정이 완료되었습니다.");

            connection.commit(); // COMMIT 수행

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }

    }

    @Override
    public void deleteBook(Book book) {
        String query = "DELETE FROM BOOK WHERE bookId = ?";

        Long bookId = book.getBookId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bookId);

            preparedStatement.executeUpdate();
            System.out.println("도서 삭제가 완료되었습니다.");

            connection.commit(); // COMMIT 수행

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
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
    public Book findById(Long originId) {
        Book book = null;

        String query = "SELECT * FROM BOOK WHERE bookId = ?";
        try {
            // PreparedStatement를 사용하여 파라미터를 바인딩하고 쿼리 실행
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, originId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 조회 결과를 Book 객체에 매핑
            if (resultSet.next()) {
                Long bookId = resultSet.getLong("bookId");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                String publicationDate = resultSet.getString("publicationDate");
                boolean isPossibleBorrow = resultSet.getBoolean("isPossibleBorrow");


                book = new Book(bookId, title, author, publisher, publicationDate, isPossibleBorrow);

            }

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book findByTitle(String title) {
        return null;
    }

    @Override
    public Book restore(Book book) {
        return null;
    }


    /**
     * 대출 가능 여부 상태 변경 메소드
     * @param status(대출 가능 여부 true/false)
     * @param bookId(대출 가능 여부 상태 변경하려는 도서의 BOOK_ID)
     */
    @Override
    public void isPossibleBorrowChange(boolean status, Long bookId) {
        String query = "UPDATE BOOK SET isPossibleBorrow = ? WHERE bookId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, Boolean.toString(status));
            preparedStatement.setLong(2, bookId);

            preparedStatement.executeUpdate();
            System.out.println("대여상태가 변경되었습니다.");

            connection.commit();

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
    }
}
