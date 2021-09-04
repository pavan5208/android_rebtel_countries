package com.rebtel.countries.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rebtel.countries.R
import com.rebtel.countries.domain.model.CountriesListResponseItem
import com.rebtel.countries.ui.adapters.viewholder.CountryViewHolder

class CountriesListAdapter(private val onCountrySelected: (model: CountriesListResponseItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val countriesList = arrayListOf<CountriesListResponseItem>()


    fun setData(value: ArrayList<CountriesListResponseItem>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(countriesList, value))
        diffResult.dispatchUpdatesTo(this)
        countriesList.clear()
        countriesList.addAll(value)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  CountryViewHolder(onCountrySelected, LayoutInflater.from(parent.context).inflate(
            R.layout.item_country,
            parent,
            false)
        )
    }

    override fun getItemCount(): Int = countriesList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CountryViewHolder).setData(countriesList[position])
    }


    inner class DiffCallback(
        private val oldList: List<CountriesListResponseItem>,
        private val newList: List<CountriesListResponseItem>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return oldItem.cioc == newItem.cioc
        }


        override fun getOldListSize(): Int {
            return oldList.size
        }


        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return oldItem == newItem && oldItem.alpha3Code == newItem.alpha3Code
        }

    }
}