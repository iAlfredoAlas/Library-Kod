package org.kodigo.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Getter
    @Setter
    private Long idUser;

    @Getter
    @Setter
    private String nameUser;

    @Getter
    @Setter
    private String userNumber;

    @Getter
    @Setter
    private String emailUser;

    @Getter
    @Setter
    private String phoneUser;

    @Getter
    @Setter
    private Boolean isActiveUser;
}
