package com.webms.demo.models;

import lombok.Getter;
import lombok.Setter;

// without Getter, auto serialization will not work
@Getter @Setter
public class Hello {
    private final String msg;
    
    public Hello(String msg) {
        this.msg = msg;
    }
}
