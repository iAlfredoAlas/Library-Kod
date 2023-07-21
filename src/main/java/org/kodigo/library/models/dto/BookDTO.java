package org.kodigo.library.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @Getter
    @Setter
    private Long idBook;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ]{0,69}+$", message = "The name must not contain spaces at the beginning, nor be longer than 70 characters.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String nameBook;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @Getter
    @Setter
    private Date publicationDate;

    @Getter
    @Setter
    private Integer totalPage;

    @Getter
    @Setter
    private Integer quantityStock;

    @NotNull(message = "The status cannot be null")
    @Getter
    @Setter
    private Boolean isActiveBook = Boolean.TRUE;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ0123456789]{0,9}+$", message = "The name must not contain spaces at the beginning, nor be longer than 10 characters.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String positionRack;

    @Getter
    @Setter
    private AuthorDTO idAuthor;

    @Getter
    @Setter
    private EditorialDTO idEditorial;

    @Getter
    @Setter
    private GenreDTO idGenre;

}
