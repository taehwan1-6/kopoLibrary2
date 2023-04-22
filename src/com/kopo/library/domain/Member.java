package com.kopo.library.domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

public class Member {
    private static AtomicLong ID_GENERATOR = new AtomicLong(1);

    private Long id;
    private String name;
    private GenderStatus gender;
    private String age;
    private String address;
    private String phoneNumber;
    private String birthDate;
    private String joinDate;

    public Member() {
    }

    // 1. Memory 모드 일때
    public Member(String name, GenderStatus gender, String address, String phoneNumber, String birthDate) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.gender = gender;
        this.age = String.valueOf(Period.between(LocalDate.now(), LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .getYears() * -1);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.joinDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    // 1. Memory 모드에서 - Id 사용해서 수정, 삭제 해줄 때
    public Member(Long id, String name, GenderStatus gender, String address, String phoneNumber, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = String.valueOf(Period.between(LocalDate.now(), LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .getYears() * -1);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    // 3. DB 모드일때
    public Member(Long id, String name, GenderStatus gender, String age, String address, String phoneNumber, String birthDate, String joinDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
    }




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderStatus getGender() {
        return gender;
    }

    public void setGender(GenderStatus gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate=" + birthDate +
                ", joinDate=" + joinDate +
                '}'+
                '\n';
    }
}
