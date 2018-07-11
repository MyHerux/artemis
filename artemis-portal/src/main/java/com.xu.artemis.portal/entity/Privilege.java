package com.xu.artemis.portal.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Privilege implements Serializable {


    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String privilegeType;

    @Column
    private String appId;
}