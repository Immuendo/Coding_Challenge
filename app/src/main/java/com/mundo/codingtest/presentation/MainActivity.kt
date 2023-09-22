package com.mundo.codingtest.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mundo.codingtest.R
import com.mundo.codingtest.data.RepositoryImpl
import com.mundo.codingtest.data.RetrofitInstance
import com.mundo.codingtest.data.api.CountryService
import com.mundo.codingtest.data.model.Country
import com.mundo.codingtest.databinding.ActivityMainBinding
import com.mundo.codingtest.domain.GetCountriesUseCase

class MainActivity : AppCompatActivity() {
    private lateinit var factory: MyViewModelFactory
    private lateinit var viewModel: MyViewModel
    private lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        manualDI()
        initRecyclerview()
    }

    private fun manualDI() {
        val countryService =
            RetrofitInstance.getRetrofitInstance().create(CountryService::class.java)
        val repository = RepositoryImpl(countryService)
        val useCase = GetCountriesUseCase(repository)
        factory = MyViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]
    }

    private fun initRecyclerview() {
        binding.countriesRv.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter()
        binding.countriesRv.adapter = adapter
        displayItems()
        binding.countriesRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                viewModel.lastPos =
                    (binding.countriesRv.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
            }
        })
    }

    private fun displayItems() {
        binding.progressBar.visibility = View.VISIBLE
        val responseLiveData: LiveData<List<Country>?> = viewModel.getCountries()
        responseLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setListItems(it)
                adapter.notifyDataSetChanged()
                if (viewModel.lastPos != null) {
                    val pos = viewModel.lastPos
                    binding.countriesRv.scrollToPosition(pos!!)
                }
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No Data Available", Toast.LENGTH_LONG).show()
            }
        })
    }
}