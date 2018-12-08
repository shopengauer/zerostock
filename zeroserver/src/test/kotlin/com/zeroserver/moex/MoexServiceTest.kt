package com.zeroserver.moex

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestOperations

@RunWith(SpringRunner::class)
@SpringBootTest
class MoexServiceTest {
    @Autowired()
    private lateinit var restTemplate: RestOperations


    @Test
    fun restTemplateTest() {


      val response  =  restTemplate.getForObject("http://iss.moex.com/iss/engines/stock/markets/shares/securities/sber/trades.json", Trades::class.java)

        println(response)

     //   val numberOfTradesOnLastPage = numberOfTrades!! - 5000 * numberOfPages!!
     //   println(numberOfTradesOnLastPage)



    }
}

