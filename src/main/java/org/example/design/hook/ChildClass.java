package org.example.design.hook;

public class ChildClass extends AbstractClass {
    @Override
    protected void abstractMethod() {
        System.out.print("子类实现父类抽象类中的抽象方法");

    }

    @Override
    public void hookMethod() {
        super.hookMethod();
    }
}
