package org.kodigo.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    @Getter
    @Setter
    private Long idAuthor;

    @Getter
    @Setter
    private String nameAuthor;

    @Getter
    @Setter
    private String nacionality;

    @Getter
    @Setter
    private Date dateBorn;

    @Getter
    @Setter
    private Boolean isActiveAuthor;
}
