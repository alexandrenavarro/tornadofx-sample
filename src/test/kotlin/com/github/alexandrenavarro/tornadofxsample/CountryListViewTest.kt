package com.github.alexandrenavarro.tornadofxsample


import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockitokotlin2.verify
import javafx.scene.Scene
import javafx.stage.Stage
import mu.KLogging
import org.junit.Test
import org.testfx.api.FxAssert.verifyThat
import org.testfx.api.FxToolkit
import org.testfx.framework.junit.ApplicationTest
import org.testfx.matcher.control.LabeledMatchers.hasText
import org.testfx.util.DebugUtils.informedErrorMessage
import tornadofx.*


class CountryListEditViewTest : ApplicationTest() {

    companion object : KLogging()

    private val countryList: MutableList<FxCountry> = ArrayList<FxCountry>().apply {
        add(FxCountry().apply { name = "name"; alpha2Code = "alpha" })
    }
    var countryListViewModel = mock<CountryListViewModel> {
        on { refreshCountries() } doReturn countryList
        on { countryList } doReturn countryList.observable()
    }

    override fun start(stage: Stage?) {
        setInScope(countryListViewModel, kclass = CountryListViewModel::class) // waiting https://github.com/edvin/tornadofx/issues/728
        val countryListEditView = CountryListEditView()
        stage!!.scene = Scene(countryListEditView.root, 100.0, 100.0)
        stage.show()
    }

    @Throws(Exception::class)
    override fun stop() {
        FxToolkit.hideStage()
    }

    @Test
    fun should_click_on_button() {
        verifyThat("refresh", hasText("refresh"), informedErrorMessage(this))
        clickOn("refresh")
        verify(countryListViewModel).refreshCountries()
    }

    @Test
    fun should_contain_refresh_button() {
        verifyThat("refresh", hasText("refresh"), informedErrorMessage(this))
    }

}