package com.kopo.library.view;

import com.kopo.library.domain.GenderStatus;
import com.kopo.library.domain.Member;
//import com.kopo.library.repository.MemberRepoImpl;
import com.kopo.library.service.MemberService;
import com.kopo.library.service.MemberServiceImpl;

import java.sql.Connection;
import java.util.Scanner;

public class MemberView {
//    private List<Member> members = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
//    Connection connection;
//    MemberService memberService = new MemberServiceImpl(connection);
//    MemberServiceDB memberService = new MemberServiceDB();

//    MemberService memberService = Main.memberService;

    MemberService memberService;

    public MemberView() {}

//    public MemberView(Connection
//    connection) {
//        this.connection = connection;
//    }

    public MemberView(MemberService memberService) {
        this.memberService = memberService;
    }

    public void view() {

        GenderStatus gender;
        Member member;

        Member deletedMember = null;
        while (true) {
            System.out.println("[0]뒤로 \t [1]회원조회 \t [2]회원등록 \t [3]회원수정 \t [4]회원삭제 \t [5]삭제취소");

            String userInput = scanner.nextLine();
            switch (userInput) {
                case ("0"): // 뒤로
                    return;
                case ("1"): // 회원조회
                    System.out.println(MainView.memberService.findAllMember());
                    break;
                case ("2"): // 회원등록
//                    System.out.println("ID을 입력해주세요");
//                    userInput = scanner.nextLine();
////                    while(memberService.isExistId(userInput)){
////                        System.out.println("이미 존재하는 ID입니다.");
////                        System.out.println("다른 ID를 입력해주세요.");
////                        userInput = scanner.nextLine();
////                    }
//                    String id = userInput;

                    System.out.println("이름을 입력해주세요");
                    userInput = scanner.nextLine();
                    String name = userInput;

                    System.out.println("성별을 입력해주세요 \t (입력예시 : MALE or FEMALE)");
                    userInput = scanner.nextLine();
                    gender = GenderStatus.valueOf(userInput);

                    System.out.println("주소를 입력해주세요 \t (입력예시 : 경기도 광명시 철산동)");
                    userInput = scanner.nextLine();
                    String address = userInput;

                    System.out.println("연락처를 입력해주세요 \t (입력예시 : 010-1234-5678)");
                    userInput = scanner.nextLine();
                    String phoneNumber = userInput;

                    System.out.println("생일을 입력해주세요 \t (입력예시 : 1996/05/11)");
                    userInput = scanner.nextLine();
                    String birthDate = userInput;
                    // 만약 입력예시처럼 입력하지 않으면 다시입력해달라는 예외처리를 해야함.
                    // 현재는 그냥 아무 String 넣어도 다 들어갈거임

                    member = new Member(name, gender, address, phoneNumber, birthDate);
                    memberService. insertMember(member);
                    break;
                case ("3"):
                    memberService.findAllMember();
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("정보를 수정할 회원의 ID를 입력해주세요");
                    userInput = scanner.nextLine();
                    Long originId = Long.valueOf(userInput);
//                    while(!memberService.isExistId(originId)){
//                        System.out.println("존재하지 않는 ID입니다.");
//                        System.out.println("다른 ID를 입력해주세요.");
//                        originId = scanner.nextLine();
//                    }
//                    memberService.findByName(originId);
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("변경될 이름을 입력해주세요");
                    userInput = scanner.nextLine();
                    name = userInput;

                    System.out.println("변경될 성별을 입력해주세요 \t (입력예시 : MALE or FEMALE)");
                    userInput = scanner.nextLine();
                    gender = GenderStatus.valueOf(userInput);

                    System.out.println("변경될 주소를 입력해주세요 \t (입력예시 : 경기도 광명시 철산동)");
                    userInput = scanner.nextLine();
                    address = userInput;

                    System.out.println("변경될 연락처를 입력해주세요 \t (입력예시 : 010-1234-5678)");
                    userInput = scanner.nextLine();
                    phoneNumber = userInput;

                    System.out.println("변경될 생일을 입력해주세요 \t (입력예시 : 1996/05/11)");
                    userInput = scanner.nextLine();
                    birthDate = userInput;
                    // 혹시나 아래와 같이 new로 새로 생성했기때문에 -> 기존의 가입날짜가 유지가 안되고 -> new로 인스턴스 생성될때의 가입날짜로 뜬다면
                    // String joinDate = memberService.findById(originId).getJoinDate();
                    // member = new Member(originId, name, gender, address, phoneNumber, birthDate, joinDate); // 이거로 처리

                    member = new Member(originId, name, gender, address, phoneNumber, birthDate);
                    memberService.updateMember(member);
                    break;
                case ("4"): // 회원삭제
                    memberService.findAllMember();
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------");
                    System.out.println("삭제할 회원의 ID을 입력해주세요.");
                    originId = scanner.nextLong();
//                    while(!memberService.isExistId(userInput)){
//                        System.out.println("존재하지 않는 ID입니다.");
//                        System.out.println("다른 ID를 입력해주세요.");
//                        userInput = scanner.nextLine();
//                    }
//                    deletedMember = memberService.createTemporaryMember(userInput);
                    deletedMember = memberService.findById(originId);
                    memberService.deleteMember(memberService.findById(originId));
                    break;
                case ("5"): // 삭제취소
                    if (deletedMember == null) {
                        System.out.println("최근 삭제된 회원 데이터가 없습니다.");
                        break;
                    } else {
                        memberService.insertMember(deletedMember);
                        System.out.println("최근 삭제된 회원 데이터가 복구되었습니다.");
                        break;
                    }
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    continue;
            }
        }
    }

}