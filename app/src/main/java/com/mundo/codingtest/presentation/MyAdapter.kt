package com.mundo.codingtest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mundo.codingtest.R
import com.mundo.codingtest.data.model.Country
import com.mundo.codingtest.databinding.CountryListItemBinding

class MyAdapter(): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val countriesList = ArrayList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CountryListItemBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.country_list_item, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return countriesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(countriesList[position])
    }

    fun setListItems(countries: List<Country>){
        countriesList.clear()
        countriesList.addAll(countries)
    }


    class MyViewHolder(private val binding: CountryListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country){
            binding.nameAndRegionTv.text = "${country.name}, ${country.region}"
            binding.codeTv.text = country.code
            binding.capitalTv.text = country.capital
        }
    }

}


