package com.zenchn.mdcoordinatedemo.ui

import android.content.Intent
import com.zenchn.mdcoordinatedemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getCoordinatorId(): Int =android.R.id.content

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun initView() {
        tv_bili.setOnClickListener { startActivity(Intent(this@MainActivity, BiliActivity::class.java)) }
        tv_zhihu.setOnClickListener { startActivity(Intent(this@MainActivity, ZhiHuActivity::class.java)) }
        tv_bdmap.setOnClickListener { startActivity(Intent(this@MainActivity, BaiDuActivity::class.java)) }
    }
}
