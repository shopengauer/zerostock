package com.zeroserver.iexservice


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.zankowski.iextrading4j.api.refdata.ExchangeSymbol
import pl.zankowski.iextrading4j.client.IEXTradingClient
import pl.zankowski.iextrading4j.client.rest.request.refdata.SymbolsRequestBuilder


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
    fun getAllSymbols() : List<ExchangeSymbol> = iexClient.executeRequest(SymbolsRequestBuilder().build())



}