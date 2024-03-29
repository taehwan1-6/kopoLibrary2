package com.kopo.library.util.dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public static Connection getConnection() {
        // 1. Oracle JDBC 드라이버를 로드합니다.
        // Connection 객체 초기화
        Connection connection = null;


        // 2. 데이터베이스에 연결합니다.
        try {
            // Oracle JDBC 드라이버를 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@localhost/xe";
            String user = "scott";
            String passwd = "tiger";

            // 데이터베이스에 연결
            connection = DriverManager.getConnection(url, user, passwd);
            connection.setAutoCommit(false); // ROLLBACK을 위한 AUTOCOMMIT FALSE 설정

            System.out.println(connection);
            System.out.println("Oracle DBMS Connection Success");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드가 실패하였습니다");
            e.printStackTrace();
            // 데이터베이스 연결 및 쿼리 실행 중 예외가 발생한 경우 처리
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결에 실패하였습니다.");
            e.printStackTrace();
        }

        return connection;
    }
}
