package com.xiewenlin.test.jvm;

class Speaker implements SpeakerInterface {
    private String hello = "";

    Speaker(String hello){
        this.hello = hello;
    }
    @Override
    public void helloTo(String somebody) {
        System.out.println(this.hello +" "+ somebody);
    }
}