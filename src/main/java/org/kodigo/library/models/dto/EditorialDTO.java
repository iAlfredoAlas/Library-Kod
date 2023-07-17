package org.kodigo.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor

public class EditorialDTO {
    @Getter
    @Setter
    private Long idEditorial;

    @Getter
    @Setter
    private String nameEditorial;

    @Getter
    @Setter
    private Date fechaAdd;
    
    @Getter
    @Setter
    private Boolean isActiveEditorial = Boolean.TRUE;
}
