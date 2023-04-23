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


    /**
     * 도서 반납 메소드
     * @param borrow(반납하려는 대출정보 borrow 객체)
     */
    @Override
    public void deleteBorrow(Borrow borrow) {
        String query = "DELETE FROM borrow WHERE borrowId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, borrow.getBorrowId());

            preparedStatement.executeUpdate();
            System.out.println("도서 반납이 완료되었습니다.");

            connection.commit(); // COMMIT 수행

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
    }

    /**
     * 대출 기한 연장 메소드
     * @param borrow(대출 기한을 연장할 대출정보 borrow 객체)
     * 대출 기한 연장하면 isPossibleExtend = 'false' 로 변경
     */
    @Override
    public void updateBorrowExtend(Borrow borrow) {
        String query = "UPDATE borrow SET isPossibleExtend = 'false', endDate = endDate + 14 WHERE borrowId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, borrow.getBorrowId());

            preparedStatement.executeUpdate();
            System.out.println("대출 기한 연장이 완료되었습니다.");

            connection.commit(); // COMMIT 수행

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
    }

    @Override
    public Borrow findById(Long originId) {
        Borrow borrow = null;

        String query = "SELECT * FROM borrow WHERE borrowId = ?";
        try {
            // PreparedStatement를 사용하여 파라미터를 바인딩하고 쿼리 실행
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, originId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 조회 결과를 Book 객체에 매핑
            if (resultSet.next()) {
                Long borrowId = resultSet.getLong("borrowId");
                Long memberId = resultSet.getLong("memberId");
                Long bookId = resultSet.getLong("bookId");
                boolean isPossibleExtend = resultSet.getBoolean("isPossibleExtend");
                String startDate = resultSet.getString("startDate");
                String endDate = resultSet.getString("endDate");


                borrow = new Borrow(borrowId, memberId, bookId, isPossibleExtend, startDate, endDate);

            }

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
        return borrow;
    }

    @Override
    public Borrow findByTitle(String title) {
        return null;
    }

    @Override
    public Long findBookId(Long borrowId) {
        String query = "SELECT bookId FROM borrow WHERE borrowId = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Long bookId = -1L; // 결과가 없을 경우 기본값으로 초기화

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, borrowId);
            resultSet = preparedStatement.executeQuery(); // 쿼리 결과

            // 쿼리 결과 처리
            if (resultSet.next()) {
                bookId = resultSet.getLong("bookId");
            }

        } catch (SQLException e) {
            System.out.println("SQL 문장 또는 DB 연결 오류가 발생했습니다.");
            e.printStackTrace();
        } finally {
            // ResultSet, Statement을 닫아줌
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookId;



//        String query = "SELECT bookId FROM borrow WHERE borrowId = ?";
//
//        Long bookId = null; // 결과가 없을 경우 기본값으로 초기화
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery())
//        {
//            preparedStatement.setLong(1, borrowId);
//
//            // 쿼리 결과 처리
//            if (resultSet.next()) {
//                bookId = resultSet.getLong("bookId");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("SQL Statement or DB Connection Error Occur");
//            e.printStackTrace();
//        }
//
//        return bookId;
    }
}
