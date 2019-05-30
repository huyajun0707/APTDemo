package com.kotlin.aptprocessor

import com.kotlin.aptdemo.CheckLogin
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import com.sun.xml.internal.bind.v2.TODO
import java.io.IOException
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

/**
 * =========================================================
 * @author      :   HuYajun     <13426236872@163.com>
 * @date        :   2019/5/29 14:54
 * @version     :
 * @description :
 * =========================================================
 */
class AptCheckLoginProcess : AbstractProcessor() {

    /**
     * /得到注解处理器可以支持的注解类型
     */
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val types = LinkedHashSet<String>()
        types.add(CheckLogin::class.java.canonicalName)
        return types
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun process(set: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        System.out.println("======>start")
        //声明一个Set集合用于储存处理类
        val hashSet = HashSet<TypeElement>()
        //获取所有被CheckLogin注解的元素
        val elements = roundEnv.getElementsAnnotatedWith(CheckLogin::class.java)
        for (annotatedElement: Element in elements) {
            if (annotatedElement.kind != ElementKind.METHOD) {
                processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "该注解只能用于类", annotatedElement)
                return false
            }
            val typeElement = annotatedElement as TypeElement

            hashSet.add(typeElement)

        }
        hashSet.iterator().forEach {
            val method = MethodSpec.methodBuilder("needLogin")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addStatement("val needLogin = \$T.\$N()", ClassName.get(LoginUtil::class.java), "needLogin")
                    .addStatement("if(needLogin) ")
                    .build()

            val checkLogin = TypeSpec.classBuilder("CheckLoginApt"+it.interfaces)
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addMethod(method)
                    .build()

            /**
             * 定义包名
             */
            val javaFile = JavaFile.builder("com.kotlin.aptdemo", checkLogin)
                    .build()
            try {
                javaFile.writeTo(processingEnv.filer)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }


        System.out.println("======>end")
        return false
    }
}