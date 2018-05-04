package com.github.alexandrenavarro.tornadofxsample

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.cloud.openfeign.support.SpringMvcContract
import tornadofx.App

class CountryApp : App(CountryView::class)

fun main(args: Array<String>) {

    val countryResource = Feign.builder()
            .encoder(JacksonEncoder())
            .decoder(JacksonDecoder())
            .contract(SpringMvcContract())
            .target(CountryResource::class.java, "https://restcountries.eu/")
    val countries = countryResource.getCountries()

    countries.forEach {
        print("${it.name} ${it.alpha2Code}")
    }

    //launch(CountryApp::class.java, *args)
}
