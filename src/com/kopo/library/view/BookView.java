package com.kopo.library.view;

import com.kopo.library.domain.GenderStatus;
import com.kopo.library.domain.Member;
import com.kopo.library.service.CrudService;
import com.kopo.library.service.MemberService;

import java.util.Scanner;

public class BookView {

    public BookView() {}

    public void view() {
        Scanner scanner = MainView.scanner;
        CrudService bookService = MainView.bookService;

        Member member;

        String name;
        GenderStatus gender;
        String address;
        String phoneNumber;
        String birthDate;

        Member deletedMember = null;

        while (true) {
            System.out.println("[0]뒤로 \t [1]도서조회 \t [2]도서등록 \t [3]도서수정 \t [4]도서삭제 \t [5]삭제취소");

            String userInput = scanner.nextLine();
            switch (userInput) {
                case ("0"): // 뒤로
                    return;
                case ("1"): // 도서조회
                    System.out.println(bookService.findAllObjects());
                    break;
//                case ("2"): // 도서등록
//                    System.out.println("이름을 입력해주세요");
//                    userInput = scanner.nextLine();
//                    name = userInput;
//
//                    System.out.println("성별을 입력해주세요 \t (입력예시 : MALE or FEMALE or ETC)");
//                    userInput = scanner.nextLine();
//                    gender = GenderStatus.valueOf(userInput);
//
//                    System.out.println("주소를 입력해주세요 \t (입력예시 : 인천광역시)");
//                    userInput = scanner.nextLine();
//                    address = userInput;
//
//                    System.out.println("연락처를 입력해주세요 \t (입력예시 : 010-1234-5678)");
//                    userInput = scanner.nextLine();
//                    phoneNumber = userInput;
//
//                    System.out.println("생일을 입력해주세요 \t (입력예시 : 1997/09/16)");
//                    userInput = scanner.nextLine();
//                    birthDate = userInput;
//
//                    member = new Member(name, gender, address, phoneNumber, birthDate);
//                    bookService.insertObjects(member);
//                    break;
//                case ("3"):
//                    bookService.findAllObjects();
//                    System.out.println(
//                            "--------------------------------------------------------------------------------------------------");
//                    System.out.println("정보를 수정할 도서의 ID를 입력해주세요");
//                    userInput = scanner.nextLine();
//                    Long originId = Long.valueOf(userInput);
//
//                    // 예외처리 - 선택한 ID가 없을 경우 추후 추가
//
//                    System.out.println(
//                            "--------------------------------------------------------------------------------------------------");
//                    System.out.println("변경될 이름을 입력해주세요");
//                    userInput = scanner.nextLine();
//                    name = userInput;
//
//                    System.out.println("변경될 성별을 입력해주세요 \t (입력예시 : MALE or FEMALE or ETC)");
//                    userInput = scanner.nextLine();
//                    gender = GenderStatus.valueOf(userInput);
//
//                    System.out.println("주소를 입력해주세요 \t (입력예시 : 인천광역시)");
//                    userInput = scanner.nextLine();
//                    address = userInput;
//
//                    System.out.println("연락처를 입력해주세요 \t (입력예시 : 010-1234-5678)");
//                    userInput = scanner.nextLine();
//                    phoneNumber = userInput;
//
//                    System.out.println("생일을 입력해주세요 \t (입력예시 : 1997/09/16)");
//                    userInput = scanner.nextLine();
//                    birthDate = userInput;
//
//                    member = new Member(originId, name, gender, address, phoneNumber, birthDate);
//                    bookService.updateMember(member);
//                    break;
//                case ("4"): // 도서삭제
//                    bookService.findAllMember();
//                    System.out.println(
//                            "--------------------------------------------------------------------------------------------------");
//                    System.out.println("삭제할 도서의 ID을 입력해주세요.");
//                    originId = scanner.nextLong();
//
//                    deletedMember = bookService.findById(originId);
//                    bookService.deleteMember(bookService.findById(originId));
//
//                    break;
//                case ("5"): // 삭제취소
//                    if (deletedMember == null) {
//                        System.out.println("최근 삭제된 도서 데이터가 없습니다.");
//                        break;
//                    } else {
//                        bookService.restore(deletedMember);
//                        System.out.println("최근 삭제된 도서 데이터가 복구되었습니다.");
//                        break;
//                    }
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }
}
