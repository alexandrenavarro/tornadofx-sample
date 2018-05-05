package com.github.alexandrenavarro.tornadofxsample

import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.*


class CountryView : View("country") {

    private val countryViewModel = CountryViewModel()
    override val root = BorderPane()

    private var nameField: TextField by singleAssign()

    val model = CountryFxModel(FxCountry())
    var country = FxCountry()


    init {
//        runAsync {
//            countryViewModel.refreshCountries()
//        } ui {
//            countryViewModel.countryList.clear()
//            countryViewModel.countryList.addAll(it)
//        }

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

                    model.rebindOnChange(this) { selectedCountry ->
                        country = selectedCountry ?: FxCountry()
                    }
                }
            }

            bottom {
                form {
                    fieldset("Edit Country") {
                        field("Name") {
                            textfield(model.name)
                        }
                        field("Alpha2Code") {
                            textfield(model.alpha2Code)
                        }
                        button("Save") {
                            enableWhen(model.dirty)
                            action {
                                model.commit()
                                country = model.country
                            }
                        }
                        button("Reset").action {
                            model.rollback()
                        }
                    }
                }
            }
        }
    }
}