package com.zenchn.mdcoordinatedemo.ui

import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.appbar.AppBarLayout
import com.zenchn.mdcoordinatedemo.R
import kotlinx.android.synthetic.main.activity_bili.*
import kotlin.math.abs


/**
 * @author:Hzj
 * @date  :2019/8/15/015
 * desc  ：仿哔哩哔哩视频播放页面
 * record：
 */
class BiliActivity : BaseActivity() {


    private enum class CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    //标记当前appbar的舒展状态
    private var mCurrentState = CollapsingToolbarLayoutState.EXPANDED

    override fun getLayoutRes(): Int = R.layout.activity_bili

    override fun getCoordinatorId(): Int = R.id.coordinator

    override fun initView() {
        //设置toolbar为actionbar
        setSupportActionBar(toolbar)

        toolbar_layout.title = "春江花月夜"
        toolbar.setNavigationOnClickListener { onBackPressed() }

        /**
         * 滑动监听
         */
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == 0) {
                if (mCurrentState != CollapsingToolbarLayoutState.EXPANDED) {
                    mCurrentState = CollapsingToolbarLayoutState.EXPANDED//修改状态标记为展开
                    toolbar_layout.title = "EXPANDED"//设置title为EXPANDED
                }
            } else if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                if (mCurrentState != CollapsingToolbarLayoutState.COLLAPSED) {
                    toolbar_layout.title = ""//设置title不显示
                    playButton.visibility = View.VISIBLE//显示播放按钮
                    mCurrentState = CollapsingToolbarLayoutState.COLLAPSED//修改状态标记为折叠
                }
            } else {
                if (mCurrentState != CollapsingToolbarLayoutState.INTERNEDIATE) {
                    if (mCurrentState == CollapsingToolbarLayoutState.COLLAPSED) {
                        playButton.visibility = View.GONE//由折叠变为中间状态时隐藏播放按钮
                    }
                    toolbar_layout.title = "INTERNEDIATE"//设置title为INTERNEDIATE
                    mCurrentState = CollapsingToolbarLayoutState.INTERNEDIATE//修改状态标记为中间
                }
            }
        })
        playButton.setOnClickListener { showMessage("开始播放...");app_bar.setExpanded(true) }
        fab.setOnClickListener { showMessage("分享...") }
    }

    /**
     * 显示菜单按钮
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bili, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> showMessage("已点赞")
            R.id.menu2 -> showMessage("已收藏")
            else -> showMessage("已投币")
        }
        return super.onOptionsItemSelected(item)
    }
}