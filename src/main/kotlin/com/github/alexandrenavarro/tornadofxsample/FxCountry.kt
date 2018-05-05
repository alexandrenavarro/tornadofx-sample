package com.github.alexandrenavarro.tornadofxsample

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class FxCountry {
    val nameProperty = SimpleStringProperty()
    var name by nameProperty
    val alpha2CodeProperty = SimpleStringProperty()
    var alpha2Code by alpha2CodeProperty


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
