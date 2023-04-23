package com.kopo.library.repository;

import com.kopo.library.domain.Book;
import com.kopo.library.domain.Borrow;
import com.kopo.library.view.MainView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepoDbImpl implements BorrowRepository {

    Connection connection = MainView.connection;


    @Override
    public List<Book> findAllBorrowAble() {
        List<Book> books = new ArrayList<>();

        String query = "SELECT * FROM BOOK "
                + "where isPossibleBorrow in 'true'"
                + "ORDER BY bookId";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query))
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
    public List<Borrow> findAllBorrow() {
        List<Borrow> borrows = new ArrayList<>();

        System.out.println("borrowId \t isPossibleExtend \t startDate \t\t\t\t endDate " +
                "\t\t \t\t title \t\t name");
        String query = "SELECT borrowId, isPossibleExtend, startDate, endDate,  "
                + "(SELECT TITLE FROM BOOK WHERE BOOK.bookId = borrow.bookId) AS title, "
                + "(SELECT NAME FROM MEMBER WHERE MEMBER.Id = borrow.memberId) AS name "
                + "FROM borrow ORDER BY endDate";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query))
        {
            // Query 결과를 처리
            while (resultSet.next()) {
                Long borrowId = resultSet.getLong("borrowId");
                boolean isPossibleExtend = resultSet.getBoolean("isPossibleExtend");
                String startDate = resultSet.getString("startDate");
                String endDate = resultSet.getString("endDate");
                String title = resultSet.getString("title");
                String name = resultSet.getString("name");

                System.out.println(borrowId + "\t\t\t\t" + isPossibleExtend + "\t\t" + "\t" + startDate + "\t\t" + endDate
                        + "\t\t" + title + "\t" + name);

//                Borrow borrow = new borrow(borrowId, isPossibleBorrow, isPossibleExtend, startDate, endDate, title, name);
//                borrows.add(borrow);
            }

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public void insertBorrow(Borrow borrow) {
        
        String query = "INSERT INTO borrow(borrowId, bookId, memberId)"
                + "VALUES(BORROW_ID_SEQ.NEXTVAL, ?, ?)";

        Long bookId = borrow.getBookId();
        Long memberId = borrow.getMemberId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query))
        {

            preparedStatement.setLong(1, bookId);
            preparedStatement.setLong(2, memberId);

            preparedStatement.executeUpdate();
            System.out.println("도서 대출이 완료되었습니다.");

            connection.commit(); // COMMIT 수행

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBorrow(Borrow borrow) {

    }

    @Override
    public void updateBorrowExtend(Borrow borrow) {

    }

    @Override
    public Borrow findById(Long id) {
        return null;
    }

    @Override
    public Borrow findByTitle(String title) {
        return null;
    }
}
