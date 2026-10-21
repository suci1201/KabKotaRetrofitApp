
package com.example.kabkotaretrofitapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kabkotaretrofitapp.adapter.KabKotaAdapter
import com.example.kabkotaretrofitapp.databinding.ActivityMainBinding
import com.example.kabkotaretrofitapp.helpers.Config
import com.example.kabkotaretrofitapp.model.KabKotaItem
import com.example.kabkotaretrofitapp.response.KabKotaResponse
import com.example.kabkotaretrofitapp.retrofit.ApiConfig
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvKabKota.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(
            this,
            layoutManager.orientation
        )
        binding.rvKabKota.addItemDecoration(itemDecoration)

        getKabKotaList()

        binding.swipeRefresh.setOnRefreshListener {
            getKabKotaList()
        }
    }

    private fun getKabKotaList() {
        showLoading(true)

        val client = ApiConfig.getApiService().getListUsers()

        client.enqueue(object : Callback<KabKotaResponse> {

            override fun onResponse(
                call: Call<KabKotaResponse>,
                response: Response<KabKotaResponse>
            ) {
                showLoading(false)

                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        setKabKotaData(
                            responseBody.data.orEmpty().filterNotNull()
                        )
                    } else {
                        Log.e(TAG, "Response body kosong")
                    }
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(
                call: Call<KabKotaResponse>,
                t: Throwable
            ) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun setKabKotaData(kabKota: List<KabKotaItem>) {
        val adapter = KabKotaAdapter()

        adapter.setOnItemClickCallback(
            object : KabKotaAdapter.OnItemClickCallback {

                override fun onItemClicked(data: KabKotaItem) {
                    val json = Gson().toJson(data)

                    val moveWithObjectIntent = Intent(
                        this@MainActivity,
                        DetailKabKotaActivity::class.java
                    )

                    moveWithObjectIntent.putExtra(
                        Config.EXTRA_KAB_KOTA,
                        json
                    )

                    startActivity(moveWithObjectIntent)
                }
            }
        )

        adapter.submitList(kabKota)
        binding.rvKabKota.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }

        binding.swipeRefresh.isRefreshing = false
    }
}
