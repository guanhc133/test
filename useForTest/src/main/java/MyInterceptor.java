import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 描述：面向切面编程（代码方式）
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/8/3 ProjectName:test Version:
 */
public class MyInterceptor implements MethodInterceptor {
    private Object target;//目标类

    public MyInterceptor(Object target) {
        this.target = target;
    }

    /**
     * 返回代理对象
     * 具体实现，暂时先不追究。
     */
    public Object createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);//回调函数  拦截器
        //设置代理对象的父类,可以看到代理对象是目标对象的子类。所以这个接口类就可以省略了。
        enhancer.setSuperclass(this.target.getClass());
        return enhancer.create();
    }

    /**
     * args 目标方法的参数
     * method 目标方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(true){//不要在意这为什么是恒成立的条件语句，为的是说明一个aop的概念：切入点。
            System.out.println("aaaaa");//切面方法a();
            //。。。
            method.invoke(this.target, objects);//调用目标类的目标方法
            //。。。
            System.out.println("bbbbb");//切面方法f();
        }
        return null;
    }

    public static void main(String[] args) {
        //目标对象
        TargetObject target = new TargetObject();
        //拦截器
        MyInterceptor myInterceptor = new MyInterceptor(target);
        //代理对象，调用cglib系统方法自动生成
        //注意：代理类是目标类的子类。
        TargetObject proxyObj = (TargetObject) myInterceptor.createProxy();
        proxyObj.business();
    }
}
