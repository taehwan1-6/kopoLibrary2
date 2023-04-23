package com.kopo.library.domain;

/**
 * 도서관 도서 대출 및 반납 관리
 */
public class Borrow {

    private Long borrowId;
    private Long memberId;
    private Long bookId;
    private boolean isPossibleExtend;
    private String startDate;
    private String endDate;

    public Borrow(Long borrowId, Long memberId, Long bookId, boolean isPossibleExtend, String startDate, String endDate) {
        this.borrowId = borrowId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.isPossibleExtend = isPossibleExtend;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Borrow(Long memberId, Long bookId, boolean isPossibleExtend) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.isPossibleExtend = isPossibleExtend;
    }

    public Borrow(Long bookId, Long memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
    }

    public Long getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Long borrowId) {
        this.borrowId = borrowId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public boolean isPossibleExtend() {
        return isPossibleExtend;
    }

    public void setPossibleExtend(boolean possibleExtend) {
        isPossibleExtend = possibleExtend;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
