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
        var buySellValue: Double = 0.0
        var futBuySellValue: Double = 0.0
        val listOfTrades = result?.trades
        var curBuySells = 0
        var average : Double = 0.0
        var prevAverage : Double = 0.0
        var index = 0
        var array = Array<Double>(50) { 0.0}

        if (listOfTrades != null) {
            IntRange(0, listOfTrades.size - 1).forEach { v ->
                if(index < 50){
                    if (listOfTrades[v].buySell == "S") {
                        array[index] = -listOfTrades[v].value
                        buySellValue -= listOfTrades[v].value
                    } else {
                        array[index] = listOfTrades[v].value
                        buySellValue += listOfTrades[v].value
                    }
                    index++
                } else {
                    average = array.sum()
                  //  println("Total = $buySellValue Avarage  = ${average} Price delta = ${listOfTrades[v].price} Relation = ${if(buySellValue > 0 ) Math.abs(average/buySellValue) else average/buySellValue}")
                    println(" Price = ${listOfTrades[v].price} Relation = ${if(average > 0 ) Math.abs(average/buySellValue) else average/buySellValue}")
                    prevAverage = average;
                    index = 0
                }

                   if (listOfTrades[v].price == BigDecimal.valueOf(193.4) || listOfTrades[v].price == BigDecimal.valueOf(193.8)) {
                   //    println("Price = ${listOfTrades[v].price} and valume = $buySellValue delta = ${listOfTrades[v].value}  ${listOfTrades[v].buySell}")

                  }
            }

        }

    }
}

