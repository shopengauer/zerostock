package com.zeroserver.iexservice


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.zankowski.iextrading4j.api.marketdata.DEEP
import pl.zankowski.iextrading4j.api.marketdata.Trade
import pl.zankowski.iextrading4j.api.refdata.ExchangeSymbol
import pl.zankowski.iextrading4j.api.stocks.Chart
import pl.zankowski.iextrading4j.api.stocks.ChartRange
import pl.zankowski.iextrading4j.api.stocks.Quote
import pl.zankowski.iextrading4j.client.IEXTradingClient
import pl.zankowski.iextrading4j.client.rest.request.marketdata.DeepRequestBuilder
import pl.zankowski.iextrading4j.client.rest.request.marketdata.TradeRequestBuilder
import pl.zankowski.iextrading4j.client.rest.request.refdata.SymbolsRequestBuilder
import pl.zankowski.iextrading4j.client.rest.request.stocks.ChartRequestBuilder
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder
import kotlin.streams.toList


/**
 * Service for IEX Trading API
 *
 * @author Pavlov-vs
 */

@Service
class IexService {

    @Autowired()
    private lateinit var iexClient: IEXTradingClient

    /**
     *  Get all symbols of companies
     *  @return list of all company symbols
     *
     */
    fun getAllSymbols(): List<ExchangeSymbol> = iexClient.executeRequest(SymbolsRequestBuilder().build())

    fun getQuotesForSymbols(symbols: Array<String>): List<Quote?> =
            symbols.toList().parallelStream().map {
                val quoteRequestBuilder = QuoteRequestBuilder().withSymbol(it).build()
                iexClient.executeRequest(quoteRequestBuilder)
            }.toList()


    fun getQuotesForSymbol(symbol: String): Quote = iexClient.executeRequest(QuoteRequestBuilder().withSymbol(symbol).build())


    fun getChart(symbol: String, chartRange: ChartRange ): List<Chart> {
        val chartRequestBuilder = ChartRequestBuilder().withSymbol(symbol).withChartRange(chartRange).build()
        return iexClient.executeRequest(chartRequestBuilder)
    }

    fun getTradesForSymbol(symbol: String) : Map<String, List<Trade>> {
        val tradeBuilder = TradeRequestBuilder().withSymbol(symbol).build()
        return iexClient.executeRequest(tradeBuilder)
    }

    fun getDeepForSymbol(vararg symbols: String) : DEEP {
        val deepBuilder = DeepRequestBuilder().withSymbols(*symbols).build()
        return iexClient.executeRequest(deepBuilder)
    }

}