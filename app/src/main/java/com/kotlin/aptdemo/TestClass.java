package com.kotlin.aptdemo;

import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import dalvik.system.DexClassLoader;

/**
 * =========================================================
 *
 * @author :   HuYajun     <13426236872@163.com>
 * @version :
 * @date :   2019/5/30 9:45
 * @description :
 * =========================================================
 */
public class TestClass {
    @CheckLogin
    public void startToDetails() {
        System.out.println("startToDetails");
    }
}
