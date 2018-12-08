package com.zeroserver.moex

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestOperations
import org.springframework.web.client.RestTemplate

@Configuration
class RestOperationsConfig {

    @Bean
    fun restTemplate() : RestOperations {
        val restTemplate = RestTemplate()
        restTemplate.messageConverters.add(mappingJacksonHttpMessageConverter())
        return restTemplate
    }

    @Bean
    fun mappingJacksonHttpMessageConverter(): MappingJackson2HttpMessageConverter {
        val converter = MappingJackson2HttpMessageConverter()
        converter.objectMapper = objectMapper()
        return converter
    }


   @Bean
   fun objectMapper() : ObjectMapper {
      val objectMapper = ObjectMapper()
      val simpleModule = SimpleModule()
      simpleModule.addDeserializer(Trades::class.java, TradeDeserializer(Trades::class.java))
      objectMapper.registerModule(simpleModule)
      return objectMapper
   }






}





//http://iss.moex.com/iss/engines/stock/markets/shares/securities/sber/trades.json