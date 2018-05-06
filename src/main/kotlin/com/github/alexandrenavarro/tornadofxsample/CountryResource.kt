package com.github.alexandrenavarro.tornadofxsample

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


interface CountryResource {

    @RequestMapping(method = [(RequestMethod.GET)], value = ["/rest/v2/all"])
    fun getCountries(): List<Country>
}