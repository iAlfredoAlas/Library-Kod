package org.kodigo.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {

    @Getter
    @Setter
    private Long idGenre;

    @Getter
    @Setter
    private String nameGenre;

    @Getter
    @Setter
    private Boolean isActiveGenre = Boolean.TRUE;
}
