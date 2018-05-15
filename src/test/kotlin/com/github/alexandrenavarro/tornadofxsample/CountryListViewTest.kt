package com.github.alexandrenavarro.tornadofxsample

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import javafx.scene.Scene
import javafx.stage.Stage
import org.junit.Test
import org.mockito.Mockito
import org.testfx.api.FxAssert.verifyThat
import org.testfx.api.FxToolkit
import org.testfx.framework.junit.ApplicationTest
import org.testfx.matcher.control.LabeledMatchers.hasText
import org.testfx.util.DebugUtils.informedErrorMessage
import tornadofx.*
import kotlin.reflect.KClass


class CountryListEditViewTest : ApplicationTest() {

    var countryResource: CountryResource? = null

    override fun start(stage: Stage?) {
        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T {
                return when (type) {
                    CountryResource::class -> {
                        var mock = Mockito.mock(type.java)
                        if (mock is CountryResource) {
                            countryResource = mock
                            whenever(mock.getCountries()).thenReturn(listOf(Country()))
                        }
                        mock
                    }
                    else -> Mockito.mock(type.java)
                }
            }
        }

        // TODO how to set the mock on CountryListEditView.countryItemViewModel instead of only mocking di() in the ViewModel
        val countryListViewModel: CountryListViewModel = mock<CountryListViewModel>()

        val countryListEditView = CountryListEditView()
        stage!!.scene = Scene(countryListEditView.root, 100.0, 100.0)
        stage.show()
    }

    @Throws(Exception::class)
    override fun stop() {
        FxToolkit.hideStage()
    }

    @Test
    fun should_contain_refresh_button() {
        verifyThat("refresh", hasText("refresh"), informedErrorMessage(this))
    }

    @Test
    fun should_click_on_button() {
        clickOn("refresh")

        //verify(countryResource)?.getCountries()

    }
}