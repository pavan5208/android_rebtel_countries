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
    fun setData(currencyType: CountriesListResponseItem) {
        itemView.apply {
            this.setOnClickListener {
                clickListener.invoke(currencyType)
            }
                GlideHandle.loadSVG(itemView.im_flag,
                    currencyType.flag?:"",
                    R.drawable.ic_loading
                )

            txt_country_name?.text = currencyType.name

        }
    }
}