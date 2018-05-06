package com.github.alexandrenavarro.tornadofxsample

import javafx.scene.layout.BorderPane
import tornadofx.*


class CountryListEditView : View("country") {

    // ViewModel for list
    private val countryViewModel = CountryListViewModel()
    // ViewModel for edit
    private val model = CountryEditViewModel(FxCountry())
    var country = FxCountry()

    override val root = BorderPane()

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
                    smartResize()
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