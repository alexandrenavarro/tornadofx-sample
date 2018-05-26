package com.github.alexandrenavarro.tornadofxsample

import javafx.scene.layout.BorderPane
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import mu.KLogging
import tornadofx.*
import kotlinx.coroutines.experimental.javafx.JavaFx as UI


open class CountryListEditView : View("country") {

    companion object : KLogging()

    // ViewModel for list
    val countryListViewModel by inject<CountryListViewModel>()
    // ViewModel for edit
    private var country = FxCountry()
    val countryItemViewModel by inject<CountryItemViewModel>()

    override val root = BorderPane()

    init {
        with(root) {
            top {
                flowpane {
                    button("refresh").action {
                        // Use with runAsync / ui of tornadofx
                        runAsync {
                            logger.info { "Country List is updating ..." }
                            countryListViewModel.refreshCountries()
                        } ui {
                            countryListViewModel.countryList.clear()
                            countryListViewModel.countryList.addAll(it)
                            logger.info { "Country List updated." }
                        }
                    }
                    button("refresh with coroutine").action {
                        // Use with coroutine
                        launch(UI) {
                            val asyncJob = async {
                                countryListViewModel.refreshCountriesSuspend()
                            }
                            // launch coroutine in UI context
                            logger.info { "Country List is updating ..." }
                            val result = asyncJob.await()
                            countryListViewModel.countryList.clear()
                            countryListViewModel.countryList.addAll(result)
                            logger.info { "Country List updated." }
                        }
                    }
                }
            }
            center {
                tableview(countryListViewModel.countryList) {
                    column("Name", FxCountry::nameProperty)
                    column("Alpha2Code", FxCountry::alpha2CodeProperty)
                    smartResize()
                    bindSelected(countryItemViewModel)
                }
            }
            bottom {
                form {
                    fieldset("Edit Country") {
                        field("Name") {
                            textfield(countryItemViewModel.name)
                        }
                        field("Alpha2Code") {
                            textfield(countryItemViewModel.alpha2Code)
                        }
                        button("Save") {
                            enableWhen(countryItemViewModel.dirty)
                            action {
                                countryItemViewModel.commit()
                                country = countryItemViewModel.item
                                logger.info { "Country saved." }
                            }
                        }
                        button("Reset").action {
                            countryItemViewModel.rollback()
                            logger.info { "Country reset." }
                        }

                    }
                }
            }
        }
        logger.info { "CountryListEditView is initiated" }
    }
}