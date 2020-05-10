package com.pg.bitcointest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pg.bitcointest.model.TransactionItem
import kotlinx.android.synthetic.main.landing_fragment.*


class LandingFragment : Fragment() {

    var isRefreshing : Boolean = false


    val adapter by lazy {
        object : BaseRecyclerAdapter<TransactionItem>() {
            override fun getLayoutId(position: Int, obj: TransactionItem): Int {
                return R.layout.item_transaction
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return object : TransactionViewHolder(view){}
            }
        }
    }


    companion object {
        fun newInstance() = LandingFragment()
    }

    private lateinit var viewModel: LandingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.landing_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LandingViewModel::class.java)



        if(this::viewModel.isInitialized) {
            rv_transactions.layoutManager = LinearLayoutManager(context)
            rv_transactions.setHasFixedSize(true)
            rv_transactions.adapter = adapter
        }



        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            val contextView: View? = view?.findViewById(R.id.landing_parent)
            contextView?.let {
                val snack = Snackbar.make(it, state.message, Snackbar.LENGTH_SHORT)
                snack.view.setBackgroundColor(ContextCompat.getColor(context!!, state.color))
                snack.show()
            }
        })


        viewModel.data.observe(viewLifecycleOwner, Observer {
            if(!isRefreshing) {
                adapter.setItems(it)
                adapter.notifyDataSetChanged()
                isRefreshing = false
            }
        })


        fab_refresh.setOnClickListener {
            viewModel.clearData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.ws.cancel()
    }

}
