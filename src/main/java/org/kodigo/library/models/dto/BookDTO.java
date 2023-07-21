package org.kodigo.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @Getter
    @Setter
    private Long idBook;

    @Getter
    @Setter
    private String nameBook;

    @Getter
    @Setter
    private Date publicationDate;

    @Getter
    @Setter
    private Integer totalPage;

    @Getter
    @Setter
    private Integer quantityStock;

    @Getter
    @Setter
    private Boolean isActiveBook = Boolean.TRUE;

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
