package com.zzz.learn.designpattern.builder;

import com.zzz.learn.designpattern.builder.domain.Man;
import com.zzz.learn.designpattern.builder.domain.Person;

public class ManBuilder implements PersonBuilder {
    private Person person;

    public ManBuilder() {
        person = new Man();
    }

    public PersonBuilder buildBody() {
        person.setBody("建造男人的身体");
        return this;
    }

    public PersonBuilder buildFoot() {
        person.setFoot("建造男人的脚");
        return this;
    }

    public PersonBuilder buildHead() {
        person.setHead("建造男人的头");
        return this;
    }

    public Person buildPerson() {
        return person;
    }
}