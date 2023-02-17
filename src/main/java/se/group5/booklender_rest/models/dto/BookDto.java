package se.group5.booklender_rest.models.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private int bookId;

    private String title;

    private boolean available;

    private boolean reserved;

    private int maxLoanDays;

    private BigDecimal finePerDay;

    private String description;

}
