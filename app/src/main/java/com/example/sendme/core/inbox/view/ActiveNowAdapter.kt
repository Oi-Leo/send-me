package com.example.sendme.core.inbox.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sendme.databinding.ActiveNowItemBinding


class ActiveNowAdapter : RecyclerView.Adapter<ActiveNowAdapter.ActiveNowView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveNowView {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActiveNowView(ActiveNowItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ActiveNowView, position: Int) {
    }

    class ActiveNowView(item: ActiveNowItemBinding) : RecyclerView.ViewHolder(item.root)
}