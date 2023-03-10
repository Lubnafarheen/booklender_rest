package se.group5.booklender_rest.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;


@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long loanId;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "loan_user_id")
    private LibraryUser loanTaker;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDate loanDate;

    private boolean concluded;

    public Loan() {
    }

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean concluded) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.concluded = concluded;
    }

    public long getLoanId() {
        return loanId;
    }

    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        if (book == null)throw new IllegalArgumentException("Book was null");
        if (!book.isAvailable())throw new IllegalArgumentException("Book is not available");
        book.setAvailable(true);
        this.book = book;
    }

    public void returnBook(Book book){
        this.book.setAvailable(true);
        this.concluded = true;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public boolean isOverdue(){
        LocalDate dueDate = loanDate.plusDays(book.getMaxLoanDays());
        return LocalDate.now().isAfter(dueDate);
    }

    public BigDecimal getFine(){
        Period period = Period.between(loanDate.plusDays(book.getMaxLoanDays()), LocalDate.now());
        int numOfDaysOverdue = period.getDays();
        BigDecimal fine = BigDecimal.ZERO;
        if (numOfDaysOverdue > 0){
            fine = BigDecimal.valueOf(numOfDaysOverdue * book.getFinePerDay().longValue());
        }
        return fine;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public boolean isConcluded() {
        return concluded;
    }

    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }

    public boolean extendLoan (int days){
        if (book.isReserved()||  isOverdue()) return false;
        if ((days > book.getMaxLoanDays())) return false;
        setLoanDate(getLoanDate().plusDays(days));
        return true;
    }

    public int daysLeftToReturnBook(){
        Period period =Period.between(loanDate.plusDays(book.getMaxLoanDays()), LocalDate.now());
        return period.getDays();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanId == loan.loanId && concluded == loan.concluded && Objects.equals(loanTaker, loan.loanTaker) && Objects.equals(book, loan.book) && Objects.equals(loanDate, loan.loanDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, loanTaker, book, loanDate, concluded);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanTaker=" + loanTaker +
                ", book=" + book +
                ", loanDate=" + loanDate +
                ", concluded=" + concluded +
                '}';
    }
}
