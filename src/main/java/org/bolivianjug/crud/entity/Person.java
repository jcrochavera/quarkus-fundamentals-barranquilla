package org.bolivianjug.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by julio.rocha on 27/6/20.
 *
 * @author julio.rocha
 */
@Entity
public class Person {
    @Id
    @Column
    public String id;
    @Column
    public Integer number;
    @Column
    public String name;
    @Column
    public String numberMessage;
}
