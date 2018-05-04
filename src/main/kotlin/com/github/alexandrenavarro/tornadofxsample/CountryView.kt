package com.github.alexandrenavarro.tornadofxsample

import javafx.collections.FXCollections
import tornadofx.View
import tornadofx.tableview

class CountryView : View("country") {
//    val countryList = FXCollections.observableArrayList(
//            Country("France", "FR", SimpleStringProperty("FR")),
//            Country("Espagne", "ES", SimpleStringProperty("ES"))
//    )

    val countryList = FXCollections.observableArrayList("")

    override val root = tableview(countryList) {
        //column("Name", Country::idProperty)
        //columnResizePolicy = SmartResize.POLICY
    }
}