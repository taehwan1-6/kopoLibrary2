package com.kopo.library.repository;

import com.kopo.library.domain.GenderStatus;
import com.kopo.library.domain.Member;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class MemberRepoCsvImpl implements MemberRepository {

    public static final String NEWLINE = System.lineSeparator(); // 개행

    /**
     * 회원 등록 메소드
     *
     * @param member
     */
    @Override
    public void insertMember(Member member) {
        BufferedWriter bufferedWriter = null;
        boolean isFileExists = new File("member.csv").exists();

        try {
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("member.csv", true), "UTF-8"));

            if (!isFileExists) {
                bufferedWriter.write("ID,이름,성별,나이,주소,연락처,생일,가입일");
                bufferedWriter.write(NEWLINE);
            }

            bufferedWriter.write(member.getId() + "," + member.getName() + "," + member.getGender() + "," + member.getAge() + ","
                    + member.getAddress() + "," + member.getPhoneNumber() + ","
                    + member.getBirthDate() + "," + member.getJoinDate());

            bufferedWriter.write(NEWLINE);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 회원 수정
     * @param member
     */
    @Override
    public void updateMember(Member member) {
        File inputFile = new File("member.csv");
        File tempFile = new File("temp.csv");

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(tempFile), "UTF-8"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                // 수정하고자 하는 회원의 Id인 경우
                if (fields[0].equals(member.getId())) {

                    // 새로운 정보로 수정
                    fields[1] = member.getName();
                    fields[2] = member.getGender().name();
                    fields[3] = member.getAddress();
                    fields[4] = member.getPhoneNumber();
                    fields[5] = member.getBirthDate();
                    fields[6] = member.getJoinDate();
                    // 새로운 정보를 csv 형식으로 작성하여 파일에 저장
                    writer.write(String.join(",", fields));
                    writer.write(NEWLINE);
                } else {
                    // 수정하지 않는 회원 정보는 그대로 유지하여 파일에 저장
                    writer.write(line);
                    writer.write(NEWLINE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 기존 csv 파일 삭제하고 수정된 파일로 변경
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    @Override
    public void deleteMember(Member member) {

    }

    /**
     * 회원조회 메소드
     * @return
     */
    @Override
    public List<Member> findAllMember() {
        List<Member> members = new ArrayList<>();
        BufferedReader bufferedReader = null;

        // csv 파일이 없을 경우의 예외처리
        if (!new File("member.csv").exists()) {
            throw new IllegalArgumentException("등록된 회원 정보가 없습니다.");
        }

        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("member.csv"), "UTF-8"));

            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] memberData = line.split(",");

                Long id = Long.parseLong(memberData[0]);
                String name = memberData[1];
                GenderStatus gender = GenderStatus.valueOf(memberData[2]);
                String age = memberData[3];
                String address = memberData[4];
                String phoneNumber = memberData[5];
                String birthDate = memberData[6];
                String joinDate = memberData[7];

                Member member = new Member(id, name, gender, age, address, phoneNumber, birthDate, joinDate);
                members.add(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return members;

    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public Member findByName(String name) {
        return null;
    }

    @Override
    public Member restore(Member member) {
        return null;
    }
}
