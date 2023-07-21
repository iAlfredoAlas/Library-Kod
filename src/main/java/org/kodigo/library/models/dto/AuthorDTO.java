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
public class AuthorDTO {

    @Getter
    @Setter
    private Long idAuthor;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ]{0,39}+$", message = "The name must not contain spaces at the beginning, nor be longer than 40 characters.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String nameAuthor;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ]{0,19}+$", message = "The name must not contain spaces at the beginning, nor be longer than 20 characters.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String nationality;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @Getter
    @Setter
    private Date dateBorn;

    @NotNull(message = "The status cannot be null")
    @Getter
    @Setter
    private Boolean isActiveAuthor;
}
