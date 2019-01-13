package com.zeroserver

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.zeroserver.iexservice.IexService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import pl.zankowski.iextrading4j.api.stocks.ChartRange
import pl.zankowski.iextrading4j.client.IEXTradingClient
import pl.zankowski.iextrading4j.client.rest.request.stocks.PriceRequestBuilder
import java.sql.Time


@RunWith(SpringRunner::class)
@SpringBootTest
class IexServiceTest {

    @Autowired()
    private lateinit var iexService: IexService
    @Autowired()
    private lateinit var iexClient: IEXTradingClient


    @Test
    fun getAllSymbols() {
        println(iexService.getAllSymbols().size)
    }

    @Test
    fun getQuote() {
     while(true) {
        val quote =  iexService.getQuotesForSymbol("AAPL")
         println("Realtime price = ${quote.iexBidPrice} Price change = ${quote.change}")
         println(quote)

         Thread.sleep(1000)
     }
      // println(iexClient.executeRequest(TopsRequestBuilder().withAllSymbols().build()))

    }

    @Test
    fun getCharts() {
        val charts = iexService.getChart("PCG", ChartRange.THREE_MONTHS)
        for (chart in charts) {
            println(chart)
        }
        println(charts.size)
    }

    @Test
    fun getDeepTest() {
        val deep = iexService.getQuotesForSymbol("AAPL")
        println(deep)

    }

    @Test
    fun getPriceTest() {
     val priceRequest =  PriceRequestBuilder().withSymbol("AAPL").build()
     val price =   iexClient.executeRequest(priceRequest)
        println(price)
        println(Time(1546466399820))

    }

    @Test
    fun getDeepForSymbol() {
       val deep = iexService.getDeepForSymbol("AAPL")

        val mapper = ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
        val jsonObject = mapper.writeValueAsString(deep)
        val prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject)
        println(prettyJson)

            }
}