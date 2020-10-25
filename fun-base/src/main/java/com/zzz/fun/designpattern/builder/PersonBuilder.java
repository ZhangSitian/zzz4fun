package com.zzz.fun.designpattern.builder;

import com.zzz.fun.designpattern.builder.domain.Person;

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
