package org.kodigo.library.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table (name = "user ")
@SQLDelete(sql = "UPDATE user  SET status_user  = false WHERE id_user=?")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    private static final long serialVersionUID = 123456779L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    @Getter
    @Setter
    private Long id_user;

    @Basic(optional = false)
    @Column(name = "name_user")
    @Getter
    @Setter
    private String name_user;

    @Basic(optional = false)
    @Column(name = "carnet_user ")
    @Getter
    @Setter
    private String carnet_user;

    @Basic(optional = false)
    @Column(name = "email_user")
    @Getter
    @Setter
    private String email_user;

    @Basic(optional = false)
    @Column(name = "phone_user")
    @Getter
    @Setter
    private String phone_user;

    @Basic(optional = false)
    @Column(name = "status_user")
    @Getter
    @Setter
    private Boolean isActiveUser = Boolean.TRUE;
}
