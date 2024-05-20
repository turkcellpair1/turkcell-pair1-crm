package com.turkcell.customerservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name= "address_name")
    private String addressName;
    @Column(name= "city")
    private String city;
    @Column(name= "district")
    private String district;
    @Column(name= "street")
    private String street;
    @Column(name= "house_number")
    private String houseNumber;
    @Column(name= "address_desc")
    private String addressDesc;
    @Column(name="default_address")
    private boolean defaultAddress;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
