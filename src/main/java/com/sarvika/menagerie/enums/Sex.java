package com.sarvika.menagerie.enums;

public enum Sex {
    m("m"),
    f("f");

    private String value;

    Sex(String str){
        this.value = str;
    }

    public String getValue(){
        return this.value;
    }
}
