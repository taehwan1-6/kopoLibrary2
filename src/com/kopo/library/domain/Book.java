package com.kopo.library.domain;

/**
 * 도서관 도서 관리
 */
public class Book {

    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private String publicationDate;
    private boolean isPossibleBorrow;

    public Book(Long bookId, String title, String author, String publisher, String publicationDate, boolean isPossibleBorrow) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.isPossibleBorrow = isPossibleBorrow;
    }

    public Book(String title, String author, String publisher, String publicationDate) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.isPossibleBorrow = true; // SQL 제약사항으로 DEFAULT랑 CHECK랑 동시적용 안되는거 같아서 여기에 default 해줌
    }

    public Book(Long bookId, String title, String author, String publisher, String publicationDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.isPossibleBorrow = true; // SQL 제약사항으로 DEFAULT랑 CHECK랑 동시적용 안되는거 같아서 여기에 default 해줌
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isPossibleBorrow() {
        return isPossibleBorrow;
    }

    public void setPossibleBorrow(boolean possibleBorrow) {
        isPossibleBorrow = possibleBorrow;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", isPossibleBorrow=" + isPossibleBorrow +
                '}' +
                '\n';
    }
}
