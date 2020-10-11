package com.zzz.fun.aspect;

import com.zzz.fun.interfaces.Contestant;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class ContestantIntroducer {
    @DeclareParents(value = "com.zzz.fun.interfaces.Performer+",
            defaultImpl = GraciousContestant.class)
    public static Contestant contestant;
}
