package com.zzz.learn.aspect;

import com.zzz.learn.interfaces.Contestant;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class ContestantIntroducer {
    @DeclareParents(value = "com.zzz.learn.interfaces.Performer+",
            defaultImpl = GraciousContestant.class)
    public static Contestant contestant;
}
