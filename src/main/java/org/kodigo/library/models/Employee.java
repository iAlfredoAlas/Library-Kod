package org.kodigo.library.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "employee")
@SQLDelete(sql = "UPDATE employee SET status_employee = false WHERE id_employee=?")
@AllArgsConstructor
@NoArgsConstructor

public class Employee implements Serializable {

    private static final long serialVersionUID = 123456799L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_employee")
    @Getter
    @Setter
    private Long idEmployee;

    @Basic(optional = false)
    @Column(name = "name_employee")
    @Getter
    @Setter
    private String nameEmployee;

    @Basic(optional = false)
    @Column(name = "employee_number")
    @Getter
    @Setter
    private String employeeNumber;

    @Basic(optional = false)
    @Column(name = "status_employee")
    @Getter
    @Setter
    private Boolean isActiveEmployee = Boolean.TRUE;

}
