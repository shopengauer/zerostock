package com.zeroserver.iexservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.zankowski.iextrading4j.client.IEXTradingClient

@Configuration
class IexConfig {

    @Bean
    fun iexClient() : IEXTradingClient = IEXTradingClient.create()


}