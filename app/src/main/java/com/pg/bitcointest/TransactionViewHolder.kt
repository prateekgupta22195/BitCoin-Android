package com.pg.bitcointest

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pg.bitcointest.model.TransactionItem

open class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    BaseRecyclerAdapter.Binder<TransactionItem> {

    var hash: TextView = itemView.findViewById(R.id.tv_hash)
    var rate: TextView = itemView.findViewById(R.id.tv_rate)
    var date: TextView = itemView.findViewById(R.id.tv_date)

    override fun bind(item: TransactionItem) {
        itemView.tag = item

        hash.text = item.hash

        date.text = item.date.toString()

        rate.text = item.rate
    }


}
