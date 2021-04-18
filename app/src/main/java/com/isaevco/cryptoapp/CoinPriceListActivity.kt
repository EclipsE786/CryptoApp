package com.isaevco.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.isaevco.cryptoapp.adapters.CoinInfoAdapter
import com.isaevco.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.isaevco.cryptoapp.pojo.CoinPriceInfo
import io.reactivex.disposables.CompositeDisposable

class CoinPriceListActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var viewModel: CoinViewModel
    private lateinit var binding: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        val view = binding.root
//        setContentView(R.layout.activity_coin_price_list)
        setContentView(view)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener{
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                Log.d("ON_CLICK_TEST", coinPriceInfo.fromSymbol)
            }

        }

        binding.rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(CoinViewModel::class.java)

        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
            Log.d("TEST_LOADING_DATA", it.toString())
        })
    }

}