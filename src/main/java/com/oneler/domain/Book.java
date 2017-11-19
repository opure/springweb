package com.oneler.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
public class Book extends AbstractPersistable<Integer> {

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "person_id",unique = true)
    private Person person;

    private String name;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {

        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
