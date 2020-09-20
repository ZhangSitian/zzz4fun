package com.zzz.learn.designpattern.builder;

import com.zzz.learn.designpattern.builder.domain.Person;

public interface PersonBuilder {
    PersonBuilder buildHead();
    PersonBuilder buildBody();
    PersonBuilder buildFoot();
    Person buildPerson();

    default Person constructPerson(PersonBuilder pb) {
        pb.buildHead();
        pb.buildBody();
        pb.buildFoot();
        return pb.buildPerson();
    }

}
