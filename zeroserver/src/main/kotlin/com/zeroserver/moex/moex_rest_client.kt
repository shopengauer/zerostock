package com.zeroserver.moex

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestOperations

@Service
class MoexRestClient{

    @Autowired()
    private lateinit var restTemplate: RestOperations


    fun getListOfTradesUrl(stockNames: List<String>, tradeNo: Long = 0) : String{
        val stocks = stockNames.joinToString(separator = ",")
        val url = "http://iss.moex.com/iss/engines/stock/markets/shares/trades.json?securities=$stocks" +
                "${if(tradeNo!= 0L) "&tradeno=$tradeNo" else ""} "

     //   val response  =  restTemplate.getForObject("http://iss.moex.com/iss/engines/stock/markets/shares/trades.json?securities=${stocks}" +
     //           "${if(tradeNo!= 0L) "&tradeno=$tradeNo" else ""} ", Trades::class.java)
       return url
    }




}