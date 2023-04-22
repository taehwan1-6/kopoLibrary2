package com.kopo.library.view;

import com.kopo.library.domain.Book;
import com.kopo.library.domain.GenderStatus;
import com.kopo.library.domain.Member;
import com.kopo.library.service.BookService;

import java.util.Scanner;

public class BookView {

    public BookView() {}

    public void view() {
        Scanner scanner = MainView.scanner;
        BookService bookService = MainView.bookService;

        Book book;

        String title;
        String author;
        String publisher;
        String publicationDate;
        boolean isPossibleBorrow;

        while (true) {
            System.out.println("[0]뒤로 \t [1]도서조회 \t [2]도서등록 \t [3]도서수정 \t [4]도서삭제 \t [5]삭제취소");

            String userInput = scanner.nextLine();
            switch (userInput) {
                case ("0"): // 뒤로
                    return;
                case ("1"): // 도서조회
                    System.out.println(bookService.findAllBook());
                    break;
                case ("2"): // 도서등록
                    System.out.println("제목을 입력해주세요");
                    userInput = scanner.nextLine();
                    title = userInput;

                    System.out.println("저자를 입력해주세요 \t ");
                    userInput = scanner.nextLine();
                    author = userInput;

                    System.out.println("출판사 입력해주세요 \t ");
                    userInput = scanner.nextLine();
                    publisher = userInput;

                    System.out.println("출판일을 입력해주세요 \t (입력예시 : yyyy/mm/dd)");
                    userInput = scanner.nextLine();
                    publicationDate = userInput;

                    book = new Book(title, author, publisher, publicationDate);
                    bookService.insertBook(book);
                    break;

                case ("3"):
                    bookService.findAllBook();
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("정보를 수정할 도서의 ID를 입력해주세요");
                    userInput = scanner.nextLine();
                    Long originId = Long.valueOf(userInput);

                    // 예외처리 - 선택한 ID가 없을 경우 추후 추가

                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("변경될 제목을 입력해주세요");
                    userInput = scanner.nextLine();
                    title = userInput;

                    System.out.println("변경될 저자를 입력해주세요 \t");
                    userInput = scanner.nextLine();
                    author = userInput;

                    System.out.println("변경될 출판사 입력해주세요 \t");
                    userInput = scanner.nextLine();
                    publisher = userInput;

                    System.out.println("변경될 출판일을 입력해주세요 \t (입력예시 : 1997/09/16)");
                    userInput = scanner.nextLine();
                    publicationDate = userInput;


                    book = new Book(originId, title, author, publisher, publicationDate);
                    bookService.updateBook(book);
                    break;
                case ("4"): // 도서삭제
                    bookService.findAllBook();

                    System.out.println("삭제할 도서의 ID을 입력해주세요.");
                    originId = scanner.nextLong();

                    bookService.deleteBook(bookService.findById(originId));

                    break;

                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }
}
