package com.zzz.learn.designpattern.builder;


import com.zzz.learn.designpattern.builder.domain.Person;
import com.zzz.learn.designpattern.builder.domain.Woman;

public class WomanBuilder implements PersonBuilder {
    private Person person;

    public WomanBuilder() {
        person = new Woman();
    }

    public PersonBuilder buildBody() {
        person.setBody("建造女人的身体");
        return this;
    }

    public PersonBuilder buildFoot() {
        person.setFoot("建造女人的脚");
        return this;
    }

    public PersonBuilder buildHead() {
        person.setHead("建造女人的头");
        return this;
    }

    public Person buildPerson() {
        return person;
    }
}