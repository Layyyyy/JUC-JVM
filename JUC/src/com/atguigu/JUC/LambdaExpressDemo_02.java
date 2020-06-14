package com.atguigu.JUC;

@FunctionalInterface
interface Foo{
   // public void sayHello();
    public int add(int x ,int y);
    default int div(int x,int y){
        System.out.println("div");
        return x/y;
    }
    public static int mav(int x,int y){
        System.out.println("mav");
        return x*y;
    }
}

/**
 * 2 lambda Express
 * 2.1 口诀
 *     前提函数式接口 接口里有且只有一个方法 default和object方法除外
 *     拷贝小括号（接口方法） 写死右箭头 落地大括号（里面写业务逻辑）
 * 2.2 @FunctionalInterface
 *
 * 2.3 1.8前接口不能有实现只能有声明，1.8之后可以允许default方法有实现
 *
 * 允许多个default
 *
 * 2.4静态方法实现
 *允许多个
 */
public class LambdaExpressDemo_02 {
    public static void main(String[] args) {
/*        Foo foo = new Foo(){

            @Override
            public void sayHello() {
                System.out.println("hello ");
            }
        };
        foo.sayHello();*/

        Foo foo = (x,y) -> {System.out.println("hello ");return x+y;};
        System.out.println(foo.add(1, 2));
        System.out.println(foo.div(10, 5));
        System.out.println(Foo.mav(2, 5));
    }
}
