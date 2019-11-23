package com.fss.demo.route.extra;

import java.io.Serializable;

public class SerializableType implements Serializable {
    public String name;
    public int age;

    public SerializableType(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
