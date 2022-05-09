package com.igojeferson.geographic.distance.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "postcodelatlng")
public class PostcodeEntity {

    @Id
    @Column
    private int id;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

}