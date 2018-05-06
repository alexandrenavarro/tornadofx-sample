package com.github.alexandrenavarro.tornadofxsample

import tornadofx.*

class CountryEditViewModel(var country: FxCountry) : ViewModel() {
    val name = bind { country.nameProperty }
    val alpha2Code = bind { country.alpha2CodeProperty }
}