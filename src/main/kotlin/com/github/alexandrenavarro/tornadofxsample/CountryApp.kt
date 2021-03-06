package com.github.alexandrenavarro.tornadofxsample

import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import javafx.application.Application.launch
import mu.KotlinLogging
import org.springframework.cloud.openfeign.support.SpringMvcContract
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import tornadofx.*
import kotlin.reflect.KClass

private val logger = KotlinLogging.logger {}
class CountryApp : App(CountryListEditView::class) {
    init {
        logger.info { "SpringContext is starting ..." }
        val context = GenericApplicationContext().apply {
            beans {
                bean<CountryResource> {
                    Feign.builder()
                            .encoder(JacksonEncoder())
                            .decoder(JacksonDecoder())
                            .contract(SpringMvcContract())
                            .target(CountryResource::class.java, "https://restcountries.eu/")


                }
            }.initialize(this)
            refresh()
        }

        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T = context.getBean(type.java)
        }
        logger.info { "SpringContext is started." }
        logger.info { "Application is started" }
    }

}

fun main(args: Array<String>) {
    logger.info { "Application is starting" }
    launch(CountryApp::class.java, *args)


}
