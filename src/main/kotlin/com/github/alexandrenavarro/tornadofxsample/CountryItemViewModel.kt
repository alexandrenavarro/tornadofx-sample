package com.github.alexandrenavarro.tornadofxsample

import tornadofx.*

class CountryItemViewModel() : ItemViewModel<FxCountry>() {
    val name = bind(FxCountry::nameProperty)
    val alpha2Code = bind(FxCountry::alpha2CodeProperty)
}
