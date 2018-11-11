package com.zeroserver

import com.zeroserver.iexservice.IexService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class IexServiceTest {

    @Autowired()
    private lateinit var iexService: IexService


    @Test
    fun getAllSymbols() {
        println(iexService.getAllSymbols())
    }
}