package org.example.design.hook;

public class SubChildClass extends ChildClass {

    @Override
    protected void abstractMethod() {
        System.out.println("我是最终子类抽象方法");
    }

    @Override
    public void hookMethod() {
        System.out.println("我是最终子类钩子方法");
    }
}
