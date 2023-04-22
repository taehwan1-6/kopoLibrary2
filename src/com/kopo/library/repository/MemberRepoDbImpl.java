package com.kopo.library.repository;

import com.kopo.library.domain.GenderStatus;
import com.kopo.library.domain.Member;
import com.kopo.library.view.MainView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberRepoDbImpl implements MemberRepository {

    public MemberRepoDbImpl() {
    }

    @Override
    public void insertMember(Member member) {

    }

    @Override
    public void updateMember(Member member) {

    }

    @Override
    public void deleteMember(Member member) {

    }

    @Override
    public List<Member> findAllMember() {
        List<Member> members = new ArrayList<>();

        System.out.println("MEMBER_ID \t NAME \t SIGN_UP_DAY \t ADDRESS \t PHONE_NUMBER \t BIRTHDAY");

        String query = "select * from member";
//        String query = "SELECT MEMBER_ID, NAME, TO_CHAR(SIGN_UP_DAY, 'YYYY/MM/DD') AS SIGN_UP_DAY, "
//                + "ADDRESS, PHONE_NUMBER, TO_CHAR(BIRTHDAY, 'YYYY/MM/DD') AS BIRTHDAY "
//                + "FROM MEMBER ORDER BY MEMBER_ID";

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = MainView.connection.createStatement();
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

                // Query 결과를 출력
                System.out.println(id + "\t" + name + "\t" + address + "\t" + phoneNumber
                        + "\t" + birthDate + "\t" + joinDate);

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

//        return null;
        return members;
    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public Member findByName(String name) {
        return null;
    }

    @Override
    public Member restore(Member member) {
        return null;
    }
}
