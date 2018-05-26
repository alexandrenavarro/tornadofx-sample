package com.github.alexandrenavarro.tornadofxsample

import mu.KLogging
import tornadofx.*

class CountryListViewModel : ViewModel() {

    companion object : KLogging()

    val countryList = arrayListOf<FxCountry>().observable()

    private val countryResource: CountryResource by di()

    fun refreshCountries(): List<FxCountry> {
        logger.info {"refreshing Countries ..."}
        Thread.sleep(1000)
        return countryResource.getCountries().toFxCountry()
    }

    suspend fun refreshCountriesSuspend(): List<FxCountry> {

        logger.info {"refreshing Countries ..."}
        Thread.sleep(1000)
        return countryResource.getCountries().toFxCountry()
    }


    // TODO can't us it if not define here, check why
    fun Country.toFxCountry(): FxCountry {
        var fxCountry = FxCountry()
        fxCountry.name = this.name
        fxCountry.alpha2Code = this.alpha2Code
        return fxCountry
    }

    fun List<Country>.toFxCountry(): List<FxCountry> {
        var fxCountryList = arrayListOf<FxCountry>()
        for (country in this) {
            fxCountryList.add(country.toFxCountry())
        }
        return fxCountryList
    }


}