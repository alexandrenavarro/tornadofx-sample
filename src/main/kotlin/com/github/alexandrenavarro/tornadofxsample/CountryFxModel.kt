package com.github.alexandrenavarro.tornadofxsample

import tornadofx.*

class CountryFxModel(var country: FxCountry) : ViewModel() {
    val name = bind { country.nameProperty }
    val alpha2Code = bind { country.alpha2CodeProperty }
}