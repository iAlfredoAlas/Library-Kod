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
public class ReserveDTO {

    @Getter
    @Setter
    private Long idReserve;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @Getter
    @Setter
    private Date dateReserve;

    @NotNull(message = "The status cannot be null")
    @Getter
    @Setter
    private Boolean isActiveReserve;

    @Getter
    @Setter
    private BookDTO idBook;

    @Getter
    @Setter
    private EmployeeDTO idEmployee;

    @Getter
    @Setter
    private UserDTO idUser;

}
