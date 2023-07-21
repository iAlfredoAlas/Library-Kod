package org.kodigo.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO  {

    @Getter
    @Setter
    private Long idEmployee;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ]{0,39}+$", message = "The name must not contain spaces at the beginning, nor be longer than 40 characters.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String nameEmployee;

    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ0123456789]{0,9}+$", message = "The name must not contain spaces at the beginning, nor be longer than 10 characters.")
    @NotBlank(message = "The field cannot remain empty")
    @Getter
    @Setter
    private String employeeNumber;

    @NotNull(message = "The status cannot be null")
    @Getter
    @Setter
    private Boolean isActiveEmployee;
}