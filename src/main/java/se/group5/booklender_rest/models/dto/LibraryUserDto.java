package se.group5.booklender_rest.models.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LibraryUserDto {

    private int userId;

    private LocalDate regDate;

    private String name;

    private String email;


}
