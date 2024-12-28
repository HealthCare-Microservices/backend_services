package com.healthcare.patient_service.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "patients",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email"), @UniqueConstraint(columnNames = "phone")})
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
        @NamedQuery(name = "Patient.findByEmail",
                query = "select p from Patient p where p.email = :email"),
        @NamedQuery(name = "Patient.findByPhone",
                query = "select p from Patient p where p.phone = :phone"),
        @NamedQuery(name = "Patient.findByEmailOrPhone",
                query = "select p from Patient p where p.email = :email OR p.phone = :phone"),
        @NamedQuery(name = "Patient.findByDobBetween",
                query = "select p from Patient p where p.dob between :startDate and :endDate")
})
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullName;
    private String email;   // select * from patients where email = 'email'
    private String phone;   // select * from patients where phone = 'phone'
    private String address;
    private LocalDate dob;

    @OneToMany( cascade = CascadeType.ALL)
//    @JoinTable(name = "patient_illness",
//            joinColumns = @JoinColumn(name = "patient_id"),
//            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Illness> illnesses;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
