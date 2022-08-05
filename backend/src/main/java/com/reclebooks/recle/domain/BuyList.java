package com.reclebooks.recle.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter @Setter
public class BuyList {

    @Id
    @GeneratedValue
    private Long id;


}
