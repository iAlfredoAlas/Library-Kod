package org.kodigo.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO  {

    @Getter
    @Setter
    private Long idEmployee;

    @Getter
    @Setter
    private String nameEmployee;

    @Getter
    @Setter
    private String employeeNumber;

    @Getter
    @Setter
    private Boolean isActiveEmployee;
}