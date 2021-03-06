package com.epam.valkaryne.spectrumevo.repository.datamodel.converters

import androidx.room.TypeConverter
import com.epam.valkaryne.spectrumevo.repository.datamodel.InvolvedCompany
import com.google.gson.Gson

/**
 * Converter to get JSON string from involved companies array and visa-versa.
 *
 * @author Valentine Litvin
 */
class InvolvedCompaniesConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromInvolvedCompanies(involvedCompanies: List<InvolvedCompany>): String {
        return gson.toJson(involvedCompanies)
    }

    @TypeConverter
    fun toInvolvedCompanies(data: String): List<InvolvedCompany> {
        return gson.fromJson(data, Array<InvolvedCompany>::class.java).toList()
    }
}