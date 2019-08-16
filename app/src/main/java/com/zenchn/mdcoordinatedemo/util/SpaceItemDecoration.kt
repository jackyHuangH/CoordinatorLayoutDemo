package com.zenchn.mdcoordinatedemo.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView


/**
 * recyclerView 垂直分割线
 */
class SpaceItemDecoration(topSpace: Int, @ColorInt decorColor: Int = Color.TRANSPARENT) :
    RecyclerView.ItemDecoration() {

    private val mSpace = topSpace
    //分割线颜色
    private val mDecorColor = decorColor
    private var mPaint: Paint = Paint()

    init {
        mPaint.isAntiAlias = true
        mPaint.color = mDecorColor
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        // 第一个item上面不需要分割线
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.set(0, mSpace, 0, 0)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        //因为getItemOffsets是针对每一个ItemView，而onDraw方法是针对RecyclerView本身，所以需要循环遍历来设置
        loop@ for (i in 0..childCount) {
            val child = parent.getChildAt(i)
            child?.let {
                val index = parent.getChildAdapterPosition(child)
                //第一个ItemView不需要绘制
                if (index > 0) {
                    val dividerTop = child.top - mSpace
                    val dividerLeft = parent.paddingLeft
                    val dividerBottom = child.top
                    val dividerRight = parent.width - parent.paddingRight
                    c.drawRect(Rect(dividerLeft, dividerTop, dividerRight, dividerBottom), mPaint)
                }
            }
        }
    }
}