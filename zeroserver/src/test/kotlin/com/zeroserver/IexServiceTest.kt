package com.zeroserver

import com.zeroserver.iexservice.IexService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pl.zankowski.iextrading4j.client.IEXTradingClient
import pl.zankowski.iextrading4j.client.rest.request.marketdata.TopsRequestBuilder
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class IexServiceTest {

    @Autowired()
    private lateinit var iexService: IexService
    @Autowired()
    private lateinit var iexClient: IEXTradingClient


    @Test
    fun getAllSymbols() {
        println(iexService.getAllSymbols())
    }

    @Test
    fun getQuote() {
      // println(iexService.getQuotesForSymbols(arrayOf("AAPL","HT-D")))
       println(iexClient.executeRequest(TopsRequestBuilder().withAllSymbols().build()))


    }
}