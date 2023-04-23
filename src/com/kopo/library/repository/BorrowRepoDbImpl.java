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
    public List<Borrow> findAllBorrowAble() {
//        List<Borrow> borrows = new ArrayList<>();
//
//        String query = "SELECT * FROM BOOK "
//                + "ORDER BY bookId";
//
//        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query);)
//        {
//            // Query 결과를 처리
//            while (resultSet.next()) {
//                Long bookId = resultSet.getLong("bookId");
//                String title = resultSet.getString("title");
//                String author = resultSet.getString("author");
//                String publisher = resultSet.getString("publisher");
//                String publicationDate = resultSet.getString("publicationDate");
//                boolean isPossibleBorrow = resultSet.getBoolean("isPossibleBorrow");
//
//                Borrow borrow = new borrow(bookId, title, author, publisher, publicationDate, isPossibleBorrow);
//                borrows.add(borrow);
//            }
//
//        } catch (SQLException e) {
//            System.out.println("SQL Statement or DB Connection Error Occur");
//            e.printStackTrace();
//        }
//
//        return borrows;

        return null;
    }

    @Override
    public List<Borrow> findAllBorrow() {
        return null;
    }

    @Override
    public void insertBorrow(Borrow borrow) {
        String query = "INSERT INTO borrow(borrowId, memberId, bookId)"
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
    public void updateBorrowExtends(Borrow borrow) {

    }

    @Override
    public void insertBook(Borrow borrow) {

    }

    @Override
    public void updateBook(Borrow borrow) {

    }

    @Override
    public void deleteBook(Borrow borrow) {

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
