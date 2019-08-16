package com.zenchn.mdcoordinatedemo.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


/**
 * @author:Hzj
 * @date  :2019/8/16/016
 * desc  ：自定义底部导航栏behavior
 * record：
 */
class MyBottomBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {

    //确定所提供的子视图是否有另一个特定的同级视图作为布局从属。
    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        //这个方法是说明这个子控件是依赖AppBarLayout的
        return dependency is AppBarLayout
    }

    //用于响应从属布局的变化
    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {

        val translationY = abs(dependency.top).toFloat()//获取依赖布局的顶部位置
        Log.d("translationY:","$translationY")
        child.translationY = translationY
        return true
    }
}