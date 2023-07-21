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
public class EditorialDTO {
    @Getter
    @Setter
    private Long idEditorial;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ]{0,39}+$", message = "The name must not contain spaces at the beginning, nor be longer than 40 characters.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String nameEditorial;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @Getter
    @Setter
    private Date dateAdd;

    @NotNull(message = "The status cannot be null")
    @Getter
    @Setter
    private Boolean isActiveEditorial;
}
