package com.zeroserver.moex

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestOperations

@Service
class MoexRestClient{

    @Autowired()
    private lateinit var restTemplate: RestOperations
 //tradeNo = 2906651700

    fun getTrades(stockNames: List<String>) :  Trades? = restTemplate.getForObject( getTradesUrl(stockNames, tradeNo = 2906651700), Trades::class.java)

    fun getTradesUrl(stockNames: List<String>, tradeNo: Long = 0) : String{
        val stocks = stockNames.joinToString(separator = ",")
        return "http://iss.moex.com/iss/engines/stock/markets/shares/trades.json?securities=$stocks" +
                "${getTradeNoText(tradeNo)} "
    }

    private fun getTradeNoText(tradeNo: Long = 0) = if(tradeNo!= 0L) "&tradeno=$tradeNo" else ""


}