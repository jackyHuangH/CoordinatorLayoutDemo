package com.zenchn.mdcoordinatedemo.ui

import android.graphics.Color
import android.view.View
import androidx.annotation.NonNull
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.zenchn.mdcoordinatedemo.R
import com.zenchn.mdcoordinatedemo.adapter.BiHuListAdapter
import com.zenchn.mdcoordinatedemo.util.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_baidu.*

/**
 * @author:Hzj
 * @date  :2019/8/16/016
 * desc  ：仿百度地图bottomsheet
 * record：
 */
class BaiDuActivity : BaseActivity() {
    private var isSetBottomSheetHeight = false

    override fun getLayoutRes(): Int = R.layout.activity_baidu

    override fun getCoordinatorId(): Int = R.id.coordinator

    override fun initView() {
        ibt_back.setOnClickListener { onBackPressed() }

        val bottomSheetBehavior = BottomSheetBehavior.from(design_bottom_sheet)
        //底栏状态改变的监听
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
                if (newState != BottomSheetBehavior.STATE_COLLAPSED && bottom_sheet_tv.visibility == View.VISIBLE) {
                    bottom_sheet_tv.visibility = View.GONE
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED && bottom_sheet_tv.visibility == View.GONE) {
                    bottom_sheet_tv.visibility = View.VISIBLE
                }
            }

            override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {
                if (bottomSheet.top < 2 * design_bottom_sheet_bar.height) {
                    //设置底栏完全展开时，出现的顶部工具栏的动画
                    design_bottom_sheet_bar.visibility = View.VISIBLE
                    design_bottom_sheet_bar.alpha = slideOffset
                    design_bottom_sheet_bar.translationY =
                        (bottomSheet.top - 2 * design_bottom_sheet_bar.height).toFloat()
                } else {
                    design_bottom_sheet_bar.visibility = View.INVISIBLE
                }
            }
        })

        design_bottom_sheet_bar.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)//点击顶部工具栏 将底栏变为折叠状态
        }

        initRecyclerView()
        initData()
    }

    private fun initData() {
        val list = ArrayList<String>()
        for (i in 0..20) {
            list.add("如意混沌${i}号分店")
        }
        val biHuListAdapter = BiHuListAdapter(list)
        rlv.adapter = biHuListAdapter
    }

    private fun initRecyclerView() {
        rlv.apply {
            layoutManager = LinearLayoutManager(this@BaiDuActivity)
            setHasFixedSize(true)
            addItemDecoration(SpaceItemDecoration(1, Color.GRAY))
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        //修改SetBottomSheet的高度 留出顶部工具栏的位置
        if (hasFocus && !isSetBottomSheetHeight) {
            val linearParams = design_bottom_sheet.layoutParams as CoordinatorLayout.LayoutParams
            val coorHeight = coordinator.height
            val barHeight = design_bottom_sheet_bar.layoutParams.height
            linearParams.height = coorHeight - barHeight
            design_bottom_sheet.layoutParams = linearParams
            isSetBottomSheetHeight = true
        }
    }
}