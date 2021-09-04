package com.rebtel.countries.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rebtel.countries.R
import com.rebtel.countries.domain.model.CountriesListResponseItem
import com.rebtel.countries.ui.adapters.viewholder.CountryViewHolder

class CountriesListAdapter(private val onCurrencySelected: (model: CountriesListResponseItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currencyTypesList = arrayListOf<CountriesListResponseItem>()


    fun setData(value: ArrayList<CountriesListResponseItem>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(currencyTypesList, value))
        diffResult.dispatchUpdatesTo(this)
        currencyTypesList.clear()
        currencyTypesList.addAll(value)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  CountryViewHolder(onCurrencySelected, LayoutInflater.from(parent.context).inflate(
            R.layout.item_country,
            parent,
            false)
        )
    }

    override fun getItemCount(): Int = currencyTypesList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CountryViewHolder).setData(currencyTypesList[position])
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