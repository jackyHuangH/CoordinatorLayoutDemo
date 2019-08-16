package com.zenchn.mdcoordinatedemo.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

/**
 * @author:Hzj
 * @date  :2019/8/15/015
 * desc  ：Activity基类
 * record：
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())

        initView()
    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initView()

    @IdRes
    abstract fun getCoordinatorId():Int

    protected fun showMessage(msg: String) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        val coordinatorLayout = findViewById<View>(getCoordinatorId())
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_SHORT).show()
    }
}