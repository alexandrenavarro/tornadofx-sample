package com.github.alexandrenavarro.tornadofxsample

import javafx.application.Application.launch
import tornadofx.*

class CountryApp : App(CountryView::class)

fun main(args: Array<String>) {

    launch(CountryApp::class.java, *args)
}
