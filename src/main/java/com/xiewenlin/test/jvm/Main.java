package com.xiewenlin.test.jvm;

public class Main{

    private final static SpeakerInterface speaker = new Speaker("Hello");

    public static void main(String[] args){
        args=new String[2];
        args[0]="World";
        speaker.helloTo(args[0]);
    }
}
