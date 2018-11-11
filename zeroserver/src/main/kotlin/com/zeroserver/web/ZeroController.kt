package com.zeroserver.web

import com.zeroserver.iexservice.IexService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/stock"])
class ZeroController {

    @Autowired()
    private lateinit var iexService: IexService


    @GetMapping(path = ["/symbols"], produces = ["application/json;charset=UTF-8"])
    fun getAllCompanySymbols() = iexService.getAllSymbols()


    @GetMapping(path = ["/quotes/{sym}"], produces = [])
    fun getQuotes(@PathVariable sym : Array<String> ) = iexService.getQuotesForSymbols(sym)

}