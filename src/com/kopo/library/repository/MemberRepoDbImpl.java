package com.kopo.library.repository;

import com.kopo.library.domain.GenderStatus;
import com.kopo.library.domain.Member;
import com.kopo.library.view.MainView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepoDbImpl implements MemberRepository {
    Connection connection = MainView.connection;

    public MemberRepoDbImpl() {
    }

    @Override
    public void insertMember(Member member) {
        String query = "INSERT INTO MEMBER (id, name, gender, address, phone_number, birthDate) " +
                "VALUES (MEMBER_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

        String name = member.getName();
        String gender = String.valueOf(member.getGender());
        String address = member.getAddress();
        String phoneNumber = member.getPhoneNumber();
        String birthDate = member.getBirthDate();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, birthDate);

            preparedStatement.executeUpdate();
            System.out.println("회원 등록이 완료되었습니다.");

            connection.commit(); // COMMIT 수행


        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
    }

    @Override
    public void updateMember(Member member) {
        String query = "UPDATE MEMBER SET NAME = ?, GENDER = ?, ADDRESS = ?, PHONE_NUMBER = ?, BIRTHDATE = ? WHERE ID = ?";

        Long id = member.getId();
        String name = member.getName();
        String gender = String.valueOf(member.getGender());
        String address = member.getAddress();
        String phoneNumber = member.getPhoneNumber();
        String birthDate = member.getBirthDate();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, birthDate);
            preparedStatement.setLong(6, id);

            preparedStatement.executeUpdate();
            System.out.println("회원 정보 수정이 완료되었습니다.");

            connection.commit(); // COMMIT 수행

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMember(Member member) {
        String query = "DELETE FROM MEMBER WHERE ID = ?";

        Long id = member.getId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            System.out.println("회원 삭제가 완료되었습니다.");

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
    }

    @Override
    public List<Member> findAllMember() {
        List<Member> members = new ArrayList<>();

        String query = "SELECT * FROM MEMBER "
                + "ORDER BY ID";

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            // Query 결과를 처리
            while (resultSet.next()) {
                Long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                GenderStatus gender = GenderStatus.valueOf(resultSet.getString("GENDER"));
                String age = resultSet.getString("AGE");
                String address = resultSet.getString("ADDRESS");
                String phoneNumber = resultSet.getString("PHONE_NUMBER");
                String birthDate = resultSet.getString("BIRTHDATE");
                String joinDate = resultSet.getString("JOINDATE");

                Member member = new Member(id, name, gender, age, address, phoneNumber, birthDate, joinDate);
                members.add(member);
            }

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        } finally {
            // ResultSet, Statement을 닫아줌
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return members;
    }

    @Override
    public Member findById(Long originId) {
        Member member = null;
        try {
            // PreparedStatement를 사용하여 파라미터를 바인딩하고 쿼리 실행
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MEMBER WHERE ID = ?");
            preparedStatement.setLong(1, originId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 조회 결과를 Member 객체에 매핑
            if (resultSet.next()) {
                Long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                GenderStatus gender = GenderStatus.valueOf(resultSet.getString("GENDER"));
                String age = resultSet.getString("AGE");
                String address = resultSet.getString("ADDRESS");
                String phoneNumber = resultSet.getString("PHONE_NUMBER");
                String birthDate = resultSet.getString("BIRTHDATE");
                String joinDate = resultSet.getString("JOINDATE");


                member = new Member(id, name, gender, age, address, phoneNumber, birthDate, joinDate);

            }

        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
        return member;

    }

    @Override
    public Member findByName(String name) {
        return null;
    }

    @Override
    public Member restore(Member member) {
        try {
            connection.rollback(); // ROLLBACK
            System.out.println("ROLLBACK");
        } catch (SQLException e) {
            System.out.println("SQL Statement or DB Connection Error Occur");
            e.printStackTrace();
        }
        return null;
    }

}
