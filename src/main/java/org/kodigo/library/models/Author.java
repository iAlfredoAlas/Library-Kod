package org.kodigo.library.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "author")
@SQLDelete(sql = "UPDATE author SET status_author = false WHERE id_author=?")
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable{
    private static final long serialVersionUID = 123456789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_author")
    @Getter
    @Setter
    private Long idAuthor;

    @Basic(optional = false)
    @Column(name = "name_author")
    @Getter
    @Setter
    private String nameAuthor;

    @Basic(optional = false)
    @Column(name = "estatus_author")
    @Getter
    @Setter
    private Boolean isActiveAuthor = Boolean.TRUE;
}
