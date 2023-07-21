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
@Table(name = "book")
@SQLDelete(sql = "UPDATE book SET status_book = false WHERE id_book=?")
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_book")
    @Getter
    @Setter
    private Long idBook;

    @Basic(optional = false)
    @Column(name = "name_book")
    @Getter
    @Setter
    private String nameBook;

    @Basic(optional = false)
    @Column(name = "publication_date")
    @Getter
    @Setter
    private Date publicationDate;

    @Basic(optional = false)
    @Column(name = "total_page")
    @Getter
    @Setter
    private Integer totalPage;

    @Basic(optional = false)
    @Column(name = "quantity_stock")
    @Getter
    @Setter
    private Integer quantityStock;

    @Basic(optional = false)
    @Column(name = "status_book")
    @Getter
    @Setter
    private Boolean isActiveBook = Boolean.TRUE;

    @Basic(optional = false)
    @Column(name = "position_rack")
    @Getter
    @Setter
    private String positionRack;

    @JoinColumn(name = "idAuthor", referencedColumnName = "id_author", foreignKey = @ForeignKey(name = "FK_book_author"))
    @ManyToOne(optional = false, targetEntity = Author.class)
    @Getter
    @Setter
    private Author idAuthor;

    @JoinColumn(name = "idEditorial", referencedColumnName = "id_editorial", foreignKey = @ForeignKey(name = "FK_book_editorial"))
    @ManyToOne(optional = false, targetEntity = Editorial.class)
    @Getter
    @Setter
    private Editorial idEditorial;

    @JoinColumn(name = "idGenre", referencedColumnName = "id_genre", foreignKey = @ForeignKey(name = "FK_book_genre"))
    @ManyToOne(optional = false, targetEntity = Genre.class)
    @Getter
    @Setter
    private Genre idGenre;

}
