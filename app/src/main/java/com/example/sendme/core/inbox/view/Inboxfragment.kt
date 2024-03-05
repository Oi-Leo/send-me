package com.example.sendme.core.inbox.view

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sendme.R
import com.example.sendme.core.authentication.view.LoginCallback
import com.example.sendme.databinding.ActiveNowItemBinding
import com.example.sendme.databinding.ActiveNowListBinding
import com.example.sendme.databinding.FragmentInboxBinding
import com.example.sendme.databinding.InboxRowItemBinding

class InboxFragment : Fragment() {

    private var _binding: FragmentInboxBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInboxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inboxAdapter = InboxAdapter()
        binding.rvInbox.adapter = inboxAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class ActiveNowAdapter : RecyclerView.Adapter<ActiveNowAdapter.ActiveNowView>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveNowView {
            return ActiveNowView(ActiveNowItemBinding.inflate(layoutInflater, parent, false))
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindViewHolder(holder: ActiveNowView, position: Int) {
        }

        private inner class ActiveNowView(val item: ActiveNowItemBinding) :
            RecyclerView.ViewHolder(item.root)
    }

    private inner class InboxAdapter : RecyclerView.Adapter<InboxAdapter.InboxView>() {

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
                    view.rvActiveNow.layoutManager =
                        LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    view.rvActiveNow.adapter = activeAdapter
                }
            }
        }
    }
}