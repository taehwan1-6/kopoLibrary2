package com.kopo.library.view;

import com.kopo.library.repository.BookRepoDbImpl;
import com.kopo.library.repository.MemberRepoDbImpl;
import com.kopo.library.service.BookServiceImpl;
import com.kopo.library.service.MemberServiceImpl;
import com.kopo.library.util.dbconnector.DBConnector;

public class ModeView {

    public void view() {
        /**
         * 모드 출력문
         */
        System.out.println("\n============================\r\n"
                + "1. Memory Mode\r\n"
                + "2. CSV Mode\r\n"
                + "3. DBMS Mode\r\n"
                + "============================\n");

        /**
         * 모드를 선택합니다.
         * 1. Memory Mode (메모리상에서 동작합니다)
         * 2. CSV Mode (CSV상에서 동작합니다)
         * 3. DBMS Mode (DBMS상에서 동작합니다)
         */

        int mode = MainView.scanner.nextInt();
        MainView.scanner.nextLine();    // Enter를 무시

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

            // DB와 연결
            MainView.connection = DBConnector.getConnection();

            MainView.memberService = new MemberServiceImpl(new MemberRepoDbImpl());
            MainView.bookService = new BookServiceImpl(new BookRepoDbImpl());
//            loanService = new LoanServiceImpl(new LoanDbmsDAOImpl());
        }

    }

}
