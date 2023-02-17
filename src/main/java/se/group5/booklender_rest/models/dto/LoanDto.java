package se.group5.booklender_rest.models.dto;

import lombok.*;
import se.group5.booklender_rest.models.Book;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {

    private Long loanId;

    private LibraryUserDto loanTaker;

    private BookDto bookDto;

    private LocalDate loanDate;

    private boolean concluded;
}
