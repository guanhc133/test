package org.example.design.hook;

public abstract class AbstractClass {

    /**
     * 具体方法,声明并实现，继承此抽象类不需实现此方法
     */

    public void concreteMethod() {
        System.out.println("这是一个具体方法");

    }

    /**
     * 抽象方法，abstract关键字标识，只声明，并不实现，继承此抽象类必须实现此方法
     */

    protected abstract void abstractMethod();

    /**
     * 钩子方法，声明并实现(空实现或者定义相关内容皆可)，继承此抽象类的子类可扩展实现或者不实现
     */

    public void hookMethod() {
//可定义一个默认操作，或者为空

        System.out.print("此钩子方法有个默认操作");

    }

    ;

    /**
     * 模板方法，整个算法的骨架
     */

    public void templateMethod() {
        abstractMethod();

        concreteMethod();

        hookMethod();

    }
}
