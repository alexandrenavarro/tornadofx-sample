package com.github.alexandrenavarro.tornadofxsample

import javafx.beans.property.SimpleStringProperty

object CountryExtentions {

    fun FxCountry.nameProperty(): SimpleStringProperty {
        return SimpleStringProperty(this, "name", name)
    }


}