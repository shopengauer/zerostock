package com.zeroserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class ZeroserverApplication

fun main(args: Array<String>) {
    runApplication<ZeroserverApplication>(*args)
}
