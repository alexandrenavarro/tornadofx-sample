package com.github.alexandrenavarro.tornadofxsample

import tornadofx.observable

class CountryListState {

    val countryList = arrayListOf<FxCountry>().observable()

    // TODO can't us it if not define here, check why
    private fun Country.toFxCountry(): FxCountry {
        var fxCountry = FxCountry()
        fxCountry.name = this.name
        fxCountry.alpha2Code = this.alpha2Code
        return fxCountry
    }

    private fun List<Country>.toFxCountry(): List<FxCountry> {
        var fxCountryList = arrayListOf<FxCountry>()
        for (country in this) {
            fxCountryList.add(country.toFxCountry())
        }
        return fxCountryList
    }

    fun updateState(countries : List<Country>) {
        countryList.clear()
        countryList.addAll(countries.toFxCountry())
    }
}