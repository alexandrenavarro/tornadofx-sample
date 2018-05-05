package com.github.alexandrenavarro.tornadofxsample

import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.*


class CountryView : View("country") {

    private val countryViewModel = CountryViewModel()
    override val root = BorderPane()

    private var nameField: TextField by singleAssign()

    init {
        with(root) {
            top {
                button("refresh").action {
                    runAsync {
                        countryViewModel.refreshCountries()
                    } ui {
                        countryViewModel.countryList.clear()
                        countryViewModel.countryList.addAll(it)
                    }
                }
            }

            center {
                tableview(countryViewModel.countryList) {
                    column("Name", FxCountry::nameProperty)
                    column("Alpha2Code", FxCountry::alpha2CodeProperty)

                }
            }

            bottom {
                form {
                    fieldset("Edit Country") {
                        field("Name") {
                            textfield() {
                                nameField = this
                            }
                        }
                        button("Save").action {
                            //save()
                            println("test")
                        }
                    }
                }
            }
        }
    }
}