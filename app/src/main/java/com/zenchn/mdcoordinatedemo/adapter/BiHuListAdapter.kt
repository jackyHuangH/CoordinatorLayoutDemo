package com.zenchn.mdcoordinatedemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zenchn.mdcoordinatedemo.R

/**
 * @author:Hzj
 * @date  :2019/8/16/016
 * desc  ：
 * record：
 */
class BiHuListAdapter(val data: List<String>?) : RecyclerView.Adapter<BiHuListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_text, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.let {
            val item = data[position]
            holder.mTv.text = item
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTv: TextView = itemView.findViewById<TextView>(R.id.tv)
    }
}