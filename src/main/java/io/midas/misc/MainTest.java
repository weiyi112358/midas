package io.midas.misc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//@MyAliasAnnotation(location = "这是值")
class MyClass {

    @MyAliasAnnotation(location = "这是值")
    public void one(){
    }

    @MyAliasAnnotation(value = "这是值")
    public void one2(){
    }
}

@AccessRole("super-user")
class MyObject1 {
}

//@ContextConfiguration
@AdminAccess
class MyObject2 {
}


interface Person {
    //交作业
    void giveTask();
    void giveTask2();
}

class Student implements Person {
    private String name;
    public Student(String name) {
        this.name = name;
    }

    public void giveTask() {
        System.out.println(name + "交语文作业");
    }

    public void giveTask2(){
        System.out.println("method2");
    }
}

//class StuInvocationHandler<T> implements InvocationHandler {
//    //invocationHandler持有的被代理对象
//    T target;
//
//    public StuInvocationHandler(T target) {
//        this.target = target;
//    }
//
//    /**
//     * proxy:代表动态代理对象
//     * method：代表正在执行的方法
//     * args：代表调用目标方法时传入的实参
//     */
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println("代理执行" +method.getName() + "方法");
//        Object result = method.invoke(target, args);
//        return result;
//    }
//}




public class MainTest {


    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {

        //MyAliasAnnotation myAnnotation = AnnotationUtils.getAnnotation(MyClass.class.getMethod("one"), MyAliasAnnotation.class);
        //AccessRole a1 = AnnotationUtils.getAnnotation(MyObject2.class,AccessRole.class);
        //AdminAccess a2 = AnnotationUtils.getAnnotation(MyObject2.class,AdminAccess.class);
        //AccessRole a3 = AnnotationUtils.getAnnotation(MyObject1.class,AccessRole.class);

        //AccessRole ann = AnnotationUtils.findAnnotation(MyObject2.class, AccessRole.class);

        //Class myClass = Class.forName("io.midas.misc.MainTest");


//        Person linqian = new Student("林浅");
//
//        //创建一个与代理对象相关联的InvocationHandler
//        InvocationHandler stuHandler = new StuInvocationHandler<Person>(linqian);
//
//        //创建一个代理对象stuProxy来代理linqian，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
//        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, stuHandler);
//
//        //代理执行交作业的方法
//        stuProxy.giveTask();
//
//        stuProxy.giveTask2();

        System.out.println(MainTest.class.getResource(""));
        System.out.println(MainTest.class.getResource("/"));






        int a = 1;



    }
}
