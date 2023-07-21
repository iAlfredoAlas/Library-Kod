package org.kodigo.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Getter
    @Setter
    private Long idUser;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ]{0,39}+$", message = "The name must not contain spaces at the beginning, nor be longer than 40 characters.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String nameUser;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ0123456789]{0,9}+$", message = "The name must not contain spaces at the beginning, nor be longer than 10 characters.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String userNumber;

    @Email(message = "The 'email' field must be a valid email address.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String emailUser;

    @Pattern(regexp = "^[0123456789][0123456789]{0,10}+$", message = "The number must not contain spaces at the beginning, nor be longer than 11 characters.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String phoneUser;

    @NotNull(message = "The status cannot be null")
    @Getter
    @Setter
    private Boolean isActiveUser;
}
