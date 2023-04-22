package com.kopo.library.view;

import java.sql.Connection;
import java.util.Scanner;

import com.kopo.library.domain.Member;
import com.kopo.library.repository.MemberRepoDbImpl;
import com.kopo.library.service.MemberService;
import com.kopo.library.service.MemberServiceImpl;
import com.kopo.library.util.dbconnector.DBConnector;

public class MainView {
    public static Scanner scanner = new Scanner(System.in);
    public static MemberService memberService;
//    public static BookService bookService;
//    public static LoanService loanService;
    public static Member prviousMember = new Member();
//    public static BookVO prviousBook = new BookVO();
    public static Connection connection = null;

//    MemberView memberView = new MemberView(connection);
    public static MemberView memberView = new MemberView();
    public static ModeView modeView = new ModeView();


    public static void main(String[] args) {
        String test = "이거가나";
//        Scanner scanner = new Scanner(System.in);

//        Connection connection = DBConnector.getConnection();

//        MemberView memberView = new MemberView(connection);
//        BookView bookView = new BookView(connection);
//        LoanView loanView = new LoanView(connection);

        System.out.println(
                "==================================================================================================");
        System.out.println("도서 대출 관리 프로그램 - 0.2.0 version");
        System.out.println(
                "==================================================================================================");

        /**
        [도서 관리 프로그램 모드 선택]
         1. Memory Mode
         2. CSV Mode
         3. DB Mode
         */
        modeView.view();

        /**
         * [도서 관리 프로그램 실행]
         * 1. 회원 관리
         * 2. 도서 관리
         * 3. 대출 관리
         * 4. 종료
         */
        while (true) {
            System.out.println("[1]회원관리\t [2]도서관리 \t [3]대출관리 \t [4]종료");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case ("1"):
                    memberView.view();
                    break;
                case ("2"):
//                    bookView.run();
//                    break;
//                case ("3"):
//                    loanView.run();
                    break;
                case ("4"):
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    System.exit(0);;
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }
}
