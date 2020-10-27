package com.company;

public abstract  class Bird extends Animal implements CanFly {
    public Bird(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(this.getName()+" is peaking");
    }

    @Override
    public void breathe() {
        System.out.println("Breathe in, breathe out, repeat");
    }

    public void fly(){
        System.out.println(getName()+" is flapping its wings");
    };
}
