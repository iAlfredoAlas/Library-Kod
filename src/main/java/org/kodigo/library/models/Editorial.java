package org.kodigo.library.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import java.io.Serializable;
@Entity
@Table (name = "editorial")
@SQLDelete(sql = "UPDATE editorial SET status_editorial = false WHERE id_editorial=?")
@AllArgsConstructor
@NoArgsConstructor
public class Editorial implements Serializable {
    private static final long serialVersionUID = 123456779L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_editorial")
    @Getter
    @Setter
    private Long idEditorial;

    @Basic(optional = false)
    @Column(name = "name_editorial")
    @Getter
    @Setter
    private String nameEditorial;

    @Basic(optional = false)
    @Column(name = "date_add")
    @Getter
    @Setter
    private Date dateAdd;

    @Basic(optional = false)
    @Column(name = "status_editorial")
    @Getter
    @Setter
    private Boolean isActiveEditorial = Boolean.TRUE;
}
