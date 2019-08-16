package com.zenchn.mdcoordinatedemo.behavior

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator


/**
 * @author:Hzj
 * @date  :2019/8/16/016
 * desc  ：自定义floatingActionButton 行为
 * record：通常自定义Behavior分为两种情况：
    1某个View依赖另一个View，监听其位置、尺寸等状态的变化。
    2某个View监听CoordinatorLayout内实现了NestedScrollingChild接口的子View的滑动状态变化(也是一种依赖关系)
 */
class MyFabBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {

    private val INTERPOLATOR = FastOutSlowInInterpolator()

    private var viewY: Float = 0.toFloat()//控件距离coordinatorLayout底部距离
    private var isAnimate: Boolean = false//动画是否在进行


    //在嵌套滑动开始前回调
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int
    ): Boolean {

        if (child.visibility == View.VISIBLE && viewY == 0f) {
            //获取控件距离父布局（coordinatorLayout）底部距离
            viewY = coordinatorLayout.height - child.y
        }

        return nestedScrollAxes and ViewCompat.SCROLL_AXIS_VERTICAL != 0//判断是否竖直滚动
    }

    //在嵌套滑动进行时，对象消费滚动距离前回调
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray
    ) {
        //dy大于0是向上滚动 小于0是向下滚动
        if (dy >= 0 && !isAnimate && child.visibility == View.VISIBLE) {
            hide(child)
        } else if (dy < 0 && !isAnimate && child.visibility == View.INVISIBLE) {
            show(child)
        }
    }

    //隐藏时的动画
    private fun hide(view: View) {
        val animator = view.animate().translationY(viewY).setInterpolator(INTERPOLATOR).setDuration(200)

        animator.setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                isAnimate = true
            }

            override fun onAnimationEnd(animator: Animator) {
                view.visibility = View.INVISIBLE
                isAnimate = false
            }

            override fun onAnimationCancel(animator: Animator) {
                show(view)
            }

            override fun onAnimationRepeat(animator: Animator) {}
        })
        animator.start()
    }

    //显示时的动画
    private fun show(view: View) {
        val animator = view.animate().translationY(0f).setInterpolator(INTERPOLATOR).setDuration(200)
        animator.setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {
                view.visibility = View.VISIBLE
                isAnimate = true
            }

            override fun onAnimationEnd(animator: Animator) {
                isAnimate = false
            }

            override fun onAnimationCancel(animator: Animator) {
                hide(view)
            }

            override fun onAnimationRepeat(animator: Animator) {}
        })
        animator.start()
    }
}