package com.kopo.library.view;

import com.kopo.library.domain.Borrow;
import com.kopo.library.service.BookService;
import com.kopo.library.service.BorrowService;
import com.kopo.library.service.MemberService;

import java.util.Scanner;

public class BorrowView {


    public BorrowView() {

    }

    public void view() {
        BorrowService borrowService = MainView.borrowService;
        BookService bookService = MainView.bookService;
        MemberService memberService = MainView.memberService;
        Scanner scanner = MainView.scanner;


        while (true) {
            System.out.println("[0]뒤로 \t [1]대출 가능한 도서 조회 \t [2]대출된 도서 조회 \t [3]도서대출 \t [4]대출연장 \t [5]도서반납");

            String userInput = scanner.nextLine();

            switch (userInput) {
                case ("0"): // 뒤로
                    return;
                case ("1"): // 대출 가능한 도서 조회
                    System.out.println(borrowService.findAllBorrowAble());
                    break;
                case ("2"): // 대출된 도서 조회
                    borrowService.findAllBorrow();
                    break;
                case ("3"): // 도서대출
                    System.out.println("대출 가능 도서 목록");
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println(borrowService.findAllBorrowAble());
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("대출될 도서의 BOOK_ID를 입력해주세요");
                    userInput = scanner.nextLine();
                    long bookId = Long.parseLong(userInput);

                    System.out.println("도서관 회원 목록");
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println(memberService.findAllMember());
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("도서 대출 신청한 회원의 MEMBER_ID를 입력해주세요");
                    userInput = scanner.nextLine();
                    long memberId = Long.parseLong(userInput);

                    Borrow borrow = new Borrow(bookId, memberId);
                    try {
                        borrowService.insertBorrow(borrow);
                    } catch (Exception e) {
                           e.printStackTrace();
                           break;
                    }
                    bookService.isPossibleBorrowChange(false, bookId); // BOOK의 대출 가능 여부 컬럼 F(불가)로 변경
                    break;
//                case ("3"): // 대출연장
//                    loanService.inquiryExtenstionableLoanData(); // 대출 기한 연장 가능 도서 목록 조회
//                    System.out.println(
//                            "--------------------------------------------------------------------------------------------------");
//                    System.out.println("대출을 연장시킬 도서의 번호를 입력해주세요.");
//                    userInput = scanner.nextLine();
//                    loanService.ExtensionDeadLine(userInput);
//                    break;
//                case ("4"): // 도서반납
//                    loanService.inquiryLoanData();
//                    System.out.println(
//                            "--------------------------------------------------------------------------------------------------");
//                    System.out.println("반납을 진행하실 대출 정보의 번호를 입력해주세요.");
//                    userInput = scanner.nextLine();
//                    bookService.changeReturnStatus("T", loanService.findBookId(userInput)); // BOOK의 대출 가능 여부 컬럼 T(가능)로 변경
//                    loanService.returnBook(userInput);
//                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }



}
