package org.kodigo.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class ReserveDTO {

    @Getter
    @Setter
    private Long idReserve;

    @Getter
    @Setter
    private Date dateReserve;

    @Getter
    @Setter
    private Boolean isActiveReserve = Boolean.TRUE;

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
