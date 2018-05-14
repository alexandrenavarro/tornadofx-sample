package com.github.alexandrenavarro.tornadofxsample

import com.nhaarman.mockitokotlin2.whenever
import mu.KLogging
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.mock
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import tornadofx.*
import kotlin.reflect.KClass
import kotlin.test.Test

class CountryListViewModelTest {

    companion object : KLogging()

    @Test
    fun test() {
        var countryListViewModel = CountryListViewModel()

        val context = GenericApplicationContext().apply {
            beans {
                bean<CountryResource> {
                    var mock = mock<CountryResource>()
                    whenever(mock.getCountries()).thenReturn(listOf(Country()))
                    mock

                }
            }.initialize(this)
            refresh()
        }

        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T = context.getBean(type.java)
        }

        // TODO improve it mockito directly in DIContainer
//        FX.dicontainer = object : DIContainer {
//            override fun <T : Any> getInstance(type: KClass<T>): T {
//                logger.info { "type:$type" }
//                TODO()
//            }
//        }

        countryListViewModel.refreshCountries().size `should be equal to` 1


    }

}