package com.kopo.library.view;

import com.kopo.library.domain.Book;
import com.kopo.library.domain.Member;
import com.kopo.library.service.CrudService;
import com.kopo.library.service.MemberService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MainView {
    // Util
    public static Scanner scanner = new Scanner(System.in);
    public static Connection connection = null;

    // Service
    public static MemberService memberService;
    public static CrudService bookService;
//    public static BookService bookService;
//    public static LoanService loanService;

    // Domain
    public static Member prviousMember = new Member();
    public static Book book = new Book();

    public static List<Book> books = new ArrayList<>();

    // View
    public static ModeView modeView = new ModeView();
    public static MemberView memberView = new MemberView();
    public static BookView bookView = new BookView();


    public static void main(String[] args) {
        System.out.println(
                "==================================================================================================");
        System.out.println("도서 관리 프로그램 - 2.0 version");
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
                    bookView.view();
                    break;
                case ("3"):
//                    loanView.run();
//                    break;
                case ("4"):
                    System.out.println("프로그램을 종료합니다.");
                    try {
                        connection.commit();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }
}
