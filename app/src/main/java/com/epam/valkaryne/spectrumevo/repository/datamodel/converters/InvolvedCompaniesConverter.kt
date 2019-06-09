package com.epam.valkaryne.spectrumevo.repository.datamodel.converters

import androidx.room.TypeConverter
import com.epam.valkaryne.spectrumevo.repository.datamodel.Company
import com.epam.valkaryne.spectrumevo.repository.datamodel.InvolvedCompany

class InvolvedCompaniesConverter {

    @TypeConverter
    fun fromInvolvedCompanies(involvedCompanies: List<InvolvedCompany>): String {
        return involvedCompanies.joinToString(separator = ",") { involvedCompany ->
            "${involvedCompany.company.name}.${involvedCompany.developer}" }
    }

    @TypeConverter
    fun toInvolvedCompanies(data: String): List<InvolvedCompany> {
        val involvedCompanies = ArrayList<InvolvedCompany>()
        val arrStr = data.split(",")
        arrStr.forEach {
            val companyStr = it.split(".")
            involvedCompanies.add(
                InvolvedCompany(
                    Company(companyStr[0]),
                    companyStr[1].toBoolean()
                )
            )
        }
        return involvedCompanies
    }
}