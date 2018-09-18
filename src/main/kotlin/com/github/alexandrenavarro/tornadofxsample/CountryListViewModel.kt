package com.github.alexandrenavarro.tornadofxsample

import mu.KLogging
import tornadofx.*

// Implementation of a ViewModel with State in order not to dependant to the view lib (javafx here)
class CountryListViewModel : ViewModel() {

    companion object : KLogging()

    val state = CountryListState()

    private val countryResource: CountryResource by di()

    fun refreshCountries(): List<Country> {
        logger.info {"refreshing Countries ..."}
        Thread.sleep(1000)
        return countryResource.getCountries()
    }

}