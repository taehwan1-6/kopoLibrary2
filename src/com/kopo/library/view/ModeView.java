package com.kopo.library.view;

import com.kopo.library.repository.MemberRepoDbImpl;
import com.kopo.library.service.MemberServiceImpl;
import com.kopo.library.util.dbconnector.DBConnector;

import java.util.Scanner;

public class ModeView {

    public void view() {
        /**
         * 모드 출력문
         *
         * 1. 테스트 모드 (저장 기능 off)
         * 2. 도서관 관리 프로그램
         */
        System.out.println("\n============================\r\n"
                + "1. 테스트 모드 (Memory Version)\r\n"
                + "2. 도서관 관리 프로그램 (CSV Version)\r\n"
                + "3. 도서관 관리 프로그램 (DBMS Version)\r\n"
                + "============================\n");

        /**
         * 모드를 선택합니다.
         * 1. 테스터 모드 (메모리상에서 동작합니다)
         * 2. CSV 버전 (CSV상에서 동작합니다)
         * 3. DBMS 버전 (DBMS상에서 동작합니다)
         */

        int mode = MainView.scanner.nextInt();
        MainView.scanner.nextLine();    // Enter를 무시
        //if (ValidationUtil.isInCorrectNum(mode, 1, 3)) continue;

        if (mode == 1) {
//            memberService = new MemberServiceImpl(new MemberDAOImpl());
//            bookService = new BookServiceImpl(new BookDAOImpl());
//            loanService = new LoanServiceImpl(new LoanDAOImpl());
        }
        else if (mode == 2) {
//            memberService = new MemberServiceImpl(new MemberCsvDAOImpl());
//            bookService = new BookServiceImpl(new BookCsvDAOImpl());
//            loanService = new LoanServiceImpl(new LoanCsvDAOImpl());
        }
        else if (mode == 3) {

            MainView.connection = DBConnector.getConnection();

//            // 데이터베이스에 연결합니다.
//            try {
//                Class.forName("oracle.jdbc.driver.OracleDriver");
//                String url = "jdbc:oracle:thin:@192.168.119.119:1521/dink";
//                String user = "scott";
//                String passwd = "tiger";
//                conn = DriverManager.getConnection(url, user, passwd);
//                System.out.println(conn);
//
//            } catch (ClassNotFoundException e) {
//                // 드라이버 로드 중 예외가 발생한 경우 처리합니다.
//                e.printStackTrace();
//            } catch (SQLException e) {
//                // 데이터베이스 연결 및 쿼리 실행 중 예외가 발생한 경우 처리합니다.
//                e.printStackTrace();
//            }

            MainView.memberService = new MemberServiceImpl(new MemberRepoDbImpl());
//            bookService = new BookServiceImpl(new BookDbmsDAOImpl());
//            loanService = new LoanServiceImpl(new LoanDbmsDAOImpl());
        }

    }

}
