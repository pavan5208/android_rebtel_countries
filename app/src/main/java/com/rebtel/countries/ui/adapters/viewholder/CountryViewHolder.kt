package com.rebtel.countries.ui.adapters.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rebtel.countries.R
import com.rebtel.countries.domain.model.CountriesListResponseItem
import com.rebtel.countries.utils.glide.GlideHandle
import kotlinx.android.synthetic.main.item_country.view.*

class CountryViewHolder(
    private val clickListener: (CountriesListResponseItem) -> Unit,
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    var countryDetailsData: CountriesListResponseItem? = null

    init {
        itemView.setOnClickListener {
            countryDetailsData?.let {
                clickListener.invoke(it)
            }
        }
    }

    fun setData(countryDetails: CountriesListResponseItem) {
        countryDetailsData = countryDetails

        itemView.apply {
            GlideHandle.loadSVG(
                itemView.im_flag,
                countryDetails.flag ?: "",
                R.drawable.ic_loading
            )

            txt_country_name?.text = countryDetails.name

        }
    }
}