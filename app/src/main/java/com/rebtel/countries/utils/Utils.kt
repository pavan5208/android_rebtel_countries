package com.rebtel.countries.utils

import com.rebtel.countries.domain.model.Currency
import com.rebtel.countries.domain.model.Language

object Utils {
    fun getLanguageNames(languages: List<Language>?):String  {
        var languageStr = ""
        languages?.forEachIndexed { index, language ->
            languageStr += language.name+", "
        }
        if(languageStr.isNotBlank()) {
            languageStr = languageStr.substring(0,languageStr.length-2)
        }
        return languageStr
    }

    fun getCurrencyData(currencies: List<Currency>?): String {
        var currencyStr = ""
        currencies?.forEachIndexed { index, currency ->
            currencyStr += ("Code : ${currency.code}\nCurrency Name : ${currency.name}\n" +
                    "Currency Symbol : ${currency.symbol}" )+", "
        }
        if(currencyStr.isNotBlank()) {
            currencyStr = currencyStr.substring(0,currencyStr.length-2)
        }
        return currencyStr
    }

    fun getBoardersData(borders: List<String>?): String{
        var borderStr = ""
        borders?.forEachIndexed { index, borderName ->
            borderStr += borderName+", "
        }
        if(borderStr.isNotBlank()) {
            borderStr = borderStr.substring(0,borderStr.length-2)
        }
        return borderStr
    }
}