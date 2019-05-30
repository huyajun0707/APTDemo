package com.kotlin.aptdemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * =========================================================
 *
 * @author :   HuYajun     <13426236872@163.com>
 * @version :
 * @date :   2019/5/30 9:18
 * @description :
 * =========================================================
 */

public class ReflectFilter {
    public static void main(String[] args) {
        try {
            parseAnno();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void  parseAnno() throws InvocationTargetException, IllegalAccessException {
        Class<TestClass> myClass = TestClass.class;
        for (Method method : myClass.getDeclaredMethods()) {
            System.out.println("--->1");
//            CheckLogin anno = method.getAnnotation(CheckLogin.class);
            boolean exists = method.isAnnotationPresent(CheckLogin.class);
            if(exists) {
                System.out.println("--->2");
                CheckLogin anno = method.getAnnotation(CheckLogin.class);
                if (anno != null) {
                    System.out.println("--->3");
                    System.out.println(method.getName());//打印方法名
                    if(LoginUtil.Companion.needLogin()){
                        System.out.println("--->to login");
                    }else {
                        method.invoke(myClass);
                    }
                    //                System.out.println(anno.value());//打印注解值
                }
            }

        }
    }
}
