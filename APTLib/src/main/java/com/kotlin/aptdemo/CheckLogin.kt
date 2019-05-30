package com.kotlin.aptdemo

/**
 * =========================================================
 * @author      :   HuYajun     <13426236872@163.com>
 * @date        :   2019/5/29 14:35
 * @version     :
 * @description :
 * =========================================================
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.RUNTIME)
annotation class CheckLogin