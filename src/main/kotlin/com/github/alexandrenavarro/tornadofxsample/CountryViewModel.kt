package com.github.alexandrenavarro.tornadofxsample

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.cloud.openfeign.support.SpringMvcContract
import tornadofx.*

class CountryViewModel : ViewModel() {

    val countryList = arrayListOf<FxCountry>().observable()

//e

    private val countryResource = Feign.builder()
            .encoder(JacksonEncoder())
            .decoder(JacksonDecoder())
            .contract(SpringMvcContract())
            .target(CountryResource::class.java, "https://restcountries.eu/")


    fun refreshCountries(): List<FxCountry> {
        Thread.sleep(2000)
        return countryResource.getCountries().toFxCountry()
    }

    // TODO 
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