package com.example.sendme.core.inbox.view

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sendme.databinding.ActiveNowListBinding
import com.example.sendme.databinding.InboxRowItemBinding


class InboxAdapter : RecyclerView.Adapter<InboxAdapter.InboxView>() {

    private val scrollState = mutableMapOf<Int, Parcelable?>()

    override fun getItemViewType(position: Int): Int {
        return if (position > 0) 1 else 0
    }

    override fun onViewRecycled(holder: InboxView) {
        super.onViewRecycled(holder)
        if (holder is ActiveNowView) {
            val key = holder.layoutPosition
            scrollState[key] = holder.view.rvActiveNow.layoutManager?.onSaveInstanceState()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = if (viewType == 0) {
            ActiveNowView(ActiveNowListBinding.inflate(layoutInflater, parent, false))
        } else {
            RowView(InboxRowItemBinding.inflate(layoutInflater, parent, false))
        }
        return view
    }

    override fun getItemCount(): Int {
        return 33
    }

    override fun onBindViewHolder(holder: InboxView, position: Int) {
        holder.bind()
    }

    abstract inner class InboxView(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind()
    }

    private inner class RowView(view: InboxRowItemBinding) : InboxView(view.root) {
        override fun bind() {

        }
    }

    private inner class ActiveNowView(val view: ActiveNowListBinding) :
        InboxView(view.root) {
        override fun bind() {
            val key = layoutPosition
            val state = scrollState[key]

            if (state != null) {
                view.rvActiveNow.layoutManager?.onRestoreInstanceState(state)
            } else {
                val activeAdapter = ActiveNowAdapter()
                view.rvActiveNow.adapter = activeAdapter
            }
        }
    }
}