package com.zeroserver.moex

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestOperations
import java.math.BigDecimal

@RunWith(SpringRunner::class)
@SpringBootTest
class MoexServiceTest {
    @Autowired()
    private lateinit var restTemplate: RestOperations
    @Autowired()
    private lateinit var moexRestClient: MoexRestClient


    @Test
    fun restTemplateTest() {


        val response = restTemplate.getForObject("http://iss.moex.com/iss/engines/stock/markets/shares/securities/sber/trades.json", Trades::class.java)
        println(response?.dataversion?.seqNum)

        //   val numberOfTradesOnLastPage = numberOfTrades!! - 5000 * numberOfPages!!
        //   println(numberOfTradesOnLastPage)

    }

    @Test
    fun moexRestClientTest() {
        val stockList = listOf("SBER")
        val result = moexRestClient.getTrades(stockList)

        var buyValue: Int = 0
        var sellValue: Int = 0

        val listOfTrades = result?.trades
        var prevSaldo = 0;
        var totalDelta = 0

        var positive = 0
        var negative = 0
        var prevPrice = 0


        if (listOfTrades != null) {
            IntRange(0, listOfTrades.size - 1).forEach { v ->
                 if (listOfTrades[v].buySell == "S") {
                        sellValue += listOfTrades[v].value.toInt()
                    } else {

                        buyValue += listOfTrades[v].value.toInt()
                    }

                    totalDelta += buyValue - sellValue - prevSaldo
                    if(listOfTrades[v].value.toInt() > prevPrice) {
                        positive++
                    } else {
                        negative++
                    }
                    println("Saldo = ${buyValue - sellValue} Price delta = ${listOfTrades[v].price} Delta: ${(buyValue - sellValue - prevSaldo )} Total delta = $totalDelta" )
                    println(positive - negative)
                    prevSaldo = buyValue - sellValue
                    prevPrice = listOfTrades[v].value.toInt()

            }

        }

    }
}

