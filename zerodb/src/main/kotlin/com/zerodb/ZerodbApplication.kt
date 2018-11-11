package com.zerodb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(DbConfig::class)
class ZerodbApplication

fun main(args: Array<String>) {
    runApplication<ZerodbApplication>(*args)
}
