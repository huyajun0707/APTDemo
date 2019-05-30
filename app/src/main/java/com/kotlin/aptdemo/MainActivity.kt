package com.kotlin.aptdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kotlin.aptdemo.aptdemo.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        LoginUtil.needLogin()
//        @CheckLogin
//        startWapActivity()
//        val proxyInvocationHandler = JavaProxyInvocationHandler(CheckLoginImpl())
//        val proxy = proxyInvocationHandler.newProxyInstance() as CheckToLogin
//        proxy.start()
        mStartBtn.setOnClickListener(View.OnClickListener {
            ReflectFilter.parseAnno()
        })

    }


    @CheckLogin
    fun startWapActivity() {

    }
}
