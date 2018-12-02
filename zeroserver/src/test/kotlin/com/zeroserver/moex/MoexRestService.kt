package com.zeroserver.moex

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.LocalTime

@RunWith(SpringRunner::class)
@SpringBootTest
class MoexRestService {

    @Test
    fun restTemplateTest() {
        val restTemplate = RestTemplate()
      val response =  restTemplate.getForObject("http://iss.moex.com/iss/engines/stock/markets/shares/securities/sber/trades.json", Trades::class.java)

        println(response)
    }
}

data class Trades(val trades: Trade, val dataversion: DataVersion)


@JsonIgnoreProperties
//@JsonRootName("trades")
data class Trade(val data: List<List<String>>)

data class TradeData(val tradeNo: Long, val tradeTime: LocalTime, val boardId: String, val stockId: String,
                     val price: BigDecimal, val quantity: Int, val value: Double, val period: String,
                     val tradeTimeGrp: Int, val sysTime: LocalDateTime, val buySell: String, val decimals: Int)

@JsonIgnoreProperties
data class DataVersion(val data: List<List<Int>>)

