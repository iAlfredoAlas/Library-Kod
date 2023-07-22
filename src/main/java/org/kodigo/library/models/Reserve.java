package org.kodigo.library.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reserve")
@SQLDelete(sql = "UPDATE reserve SET status_reserve = false WHERE id_reserve=?")
@AllArgsConstructor
@NoArgsConstructor
public class Reserve implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reserve")
    @Getter
    @Setter
    private Long idReserve;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @Basic(optional = false)
    @Column(name = "date_reserve")
    @Getter
    @Setter
    private Date dateReserve;

    @Basic(optional = false)
    @Column(name = "status_reserve")
    @Getter
    @Setter
    private Boolean isActiveReserve = Boolean.TRUE;

    @JoinColumn(name = "idBook", referencedColumnName = "id_book", foreignKey = @ForeignKey(name = "FK_reserve_book"))
    @ManyToOne(optional = false, targetEntity = Book.class)
    @Getter
    @Setter
    private Book idBook;

    @JoinColumn(name = "idEmployee", referencedColumnName = "id_employee", foreignKey = @ForeignKey(name = "FK_reserve_employee"))
    @ManyToOne(optional = false, targetEntity = Employee.class)
    @Getter
    @Setter
    private Employee idEmployee;

    @JoinColumn(name = "idUser", referencedColumnName = "id_user", foreignKey = @ForeignKey(name = "FK_reserve_user"))
    @ManyToOne(optional = false, targetEntity = User.class)
    @Getter
    @Setter
    private User idUser;
}
