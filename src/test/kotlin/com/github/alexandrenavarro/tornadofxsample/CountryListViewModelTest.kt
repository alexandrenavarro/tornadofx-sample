package com.github.alexandrenavarro.tornadofxsample

//import org.amshove.kluent.mock
import com.nhaarman.mockitokotlin2.whenever
import mu.KLogging
import org.amshove.kluent.`should be equal to`
import org.mockito.Mockito
import tornadofx.*
import kotlin.reflect.KClass
import kotlin.test.Test

class CountryListViewModelTest {

    companion object : KLogging()

    @Test
    fun test() {
        var countryListViewModel = CountryListViewModel()

// with mocking in a context spring
//        val context = GenericApplicationContext().apply {
//            beans {
//                bean<CountryResource> {
//                    var mock = mock<CountryResource>()
//                    whenever(mock.getCountries()).thenReturn(listOf(Country()))
//                    mock
//
//                }
//            }.initialize(this)
//            refresh()
//        }
//
//        FX.dicontainer = object : DIContainer {
//            override fun <T : Any> getInstance(type: KClass<T>): T = context.getBean(type.java)
//        }

// with mocking direct with DIContainer

        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T {
                return when (type) {
                    CountryResource::class -> {
                        var mock = Mockito.mock(type.java)
                        if (mock is CountryResource) {
                            whenever(mock.getCountries()).thenReturn(listOf(Country()))
                        }
                        mock
                    }
                    else -> Mockito.mock(type.java)
                }
            }

        }

        countryListViewModel.refreshCountries().size `should be equal to` 1


    }

}