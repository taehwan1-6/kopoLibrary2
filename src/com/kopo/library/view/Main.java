package com.kopo.library.view;

import java.sql.Connection;
import java.util.Scanner;

import com.kopo.library.dbconnector.DBConnector;

public class Main {

    public static void main(String[] args) {
        String test = "이거가나";
        Scanner scanner = new Scanner(System.in);

        Connection connetion = DBConnector.getConnection();

        MemberView memberView = new MemberView(connetion);
//        BookView bookView = new BookView(connetion);
//        LoanView loanView = new LoanView(connetion);

        System.out.println(
                "==================================================================================================");
        System.out.println("도서 대출 관리 프로그램 - 0.2.0 version");
        System.out.println(
                "==================================================================================================");

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
