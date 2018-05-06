package com.github.alexandrenavarro.tornadofxsample

import javafx.scene.layout.BorderPane
import mu.KLogging
import tornadofx.*


class CountryListEditView : View("country") {

    companion object : KLogging()

    // ViewModel for list
    private val countryListViewModel = CountryListViewModel()
    // ViewModel for edit
    private val countryViewModel = CountryEditViewModel(FxCountry())
    private var country = FxCountry()

    override val root = BorderPane()

    init {

        with(root) {
            top {
                button("refresh").action {
                    runAsync {
                        logger.info { "Country List is updating ..." }
                        countryListViewModel.refreshCountries()
                    } ui {
                        countryListViewModel.countryList.clear()
                        countryListViewModel.countryList.addAll(it)
                        logger.info { "Country List updated." }
                    }
                }
            }
            center {
                tableview(countryListViewModel.countryList) {
                    column("Name", FxCountry::nameProperty)
                    column("Alpha2Code", FxCountry::alpha2CodeProperty)
                    smartResize()
                    countryViewModel.rebindOnChange(this) { selectedCountry ->
                        country = selectedCountry ?: FxCountry()
                    }

                }
            }
            bottom {
                form {
                    fieldset("Edit Country") {
                        field("Name") {
                            textfield(countryViewModel.name)
                        }
                        field("Alpha2Code") {
                            textfield(countryViewModel.alpha2Code)
                        }
                        button("Save") {
                            enableWhen(countryViewModel.dirty)
                            action {
                                countryViewModel.commit()
                                country = countryViewModel.country
                                logger.info { "Country saved." }
                            }
                        }
                        button("Reset").action {
                            countryViewModel.rollback()
                            logger.info { "Country reset." }
                        }
                    }
                }
            }
        }
        logger.info { "CountryListEditView is initiated" }
    }
}