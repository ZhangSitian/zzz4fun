package com.zzz.learn.jdk8.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Working with java types from javascript.
 *
 * @author Benjamin Winterberg
 */
public class Nashorn4 {

    public static void main(String[] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("loadWithNewGlobal('nashorn/nashorn4.js')");
    }

}