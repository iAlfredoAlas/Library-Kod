package org.kodigo.library.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "genre")
@SQLDelete(sql = "UPDATE genre SET status_genre = false WHERE id_genre=?")
@AllArgsConstructor
@NoArgsConstructor
public class Genre implements Serializable {
    private static final long serialVersionUID = 123456789L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_genre")
    @Getter
    @Setter
    private Long idGenre;

    @Basic(optional = false)
    @Column(name = "name_genre")
    @Getter
    @Setter
    private String nameGenre;

    @Basic(optional = false)
    @Column(name = "status_genre")
    @Getter
    @Setter
    private Boolean isActiveGenre = Boolean.TRUE;
}
