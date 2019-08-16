package com.zenchn.mdcoordinatedemo.ui

import android.graphics.Color
import androidx.core.view.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import com.zenchn.mdcoordinatedemo.R
import com.zenchn.mdcoordinatedemo.adapter.BiHuListAdapter
import com.zenchn.mdcoordinatedemo.util.SpaceItemDecoration
import kotlinx.android.synthetic.main.activity_bihu.*

/**
 * @author:Hzj
 * @date  :2019/8/16/016
 * desc  ：
 * record：
 */
class ZhiHuActivity : BaseActivity() {
    override fun getLayoutRes(): Int = R.layout.activity_bihu

    override fun getCoordinatorId(): Int = R.id.coordinator

    override fun initView() {
        toolbar.title = "知乎"
        toolbar.setNavigationOnClickListener { onBackPressed() }
        swipe_refresh.setOnRefreshListener {
            swipe_refresh.postDelayed(1000) {
                swipe_refresh.isRefreshing = false
                showMessage("刷新成功！")
            }
        }

        initRecyclerView()
        initData()
    }

    private fun initData() {
        val list = ArrayList<String>()
        for (i in 0..20) {
            list.add("知乎热门排行榜--$i")
        }
        val biHuListAdapter = BiHuListAdapter(list)
        rlv.adapter = biHuListAdapter
    }

    private fun initRecyclerView() {
        rlv.apply {
            layoutManager = LinearLayoutManager(this@ZhiHuActivity)
            setHasFixedSize(true)
            addItemDecoration(SpaceItemDecoration(1, Color.GRAY))
        }
    }
}