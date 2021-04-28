package org.example.design.hook;

public class ChildClassDemo {

    public void bond(AbstractClass abstractClass){
        abstractClass.templateMethod();

    }

    public static void main(String[] args) {
        ChildClassDemo demo = new ChildClassDemo();
        SubChildClass sub = new SubChildClass();
        sub.templateMethod();
//        demo.bond(new AbstractClass() {
//            @Override
//            protected void abstractMethod() {
//                System.out.println("匿名内部类实现");
//            }
//
//            @Override
//            public void hookMethod() {
//                System.out.println("重构钩子方法");
//            }
//        });
    }
}
