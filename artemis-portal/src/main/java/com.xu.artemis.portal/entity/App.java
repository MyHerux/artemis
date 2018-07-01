package com.xu.artemis.portal.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class App implements Serializable {


    private static final long serialVersionUID = 1946995407873438016L;

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String owner;

    @Column
    private String ownerPhone;

    @Column
    private String ownerMail;

    @Column
    private Date createTimestamp;

    @Column
    private Date lastUpdatedTimestamp;
}