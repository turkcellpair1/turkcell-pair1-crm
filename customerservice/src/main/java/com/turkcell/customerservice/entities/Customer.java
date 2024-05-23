package com.turkcell.customerservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "nationality_id")
    private String nationalityId;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "gender")
    private String gender;
    @Column(name= "status",columnDefinition = "boolean default true")
    private Boolean status;
    @Column(name = "user_id",unique = true)
    private int user_id;

    @OneToOne(mappedBy = "customer")
    private Contact contact;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;
}
