package com.zeroserver.moex

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.IOException
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.LocalTime


/**
 * Class that represents trades from MOEX
 * @param trades list of trades entries from MOEX
 * @param dataversion object with info about total number of entries
 *
 */
@JsonDeserialize(using = TradeDeserializer::class)
data class Trades(val trades: List<TradeEntry>, val dataversion: DataVersion)

/**
 * Trade entry from MOEX
 *
 * @param tradeNo number of trade
 * @param tradeTime time of trade
 *
 */
data class TradeEntry(val tradeNo: Long, val tradeTime: LocalTime, val boardId: String, val stockId: String,
                      val price: BigDecimal, val quantity: Int, val value: Double, val period: String,
                      val tradeTimeGrp: Int, val sysTime: LocalDateTime, val buySell: String, val decimals: Int)

/**
 *
 */
data class DataVersion(val version: Int = 0, val seqNum: Int = 0)


/**
 * Deserializer for Trades object
 * @author Pavlov-VS
 */
class TradeDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<Trades>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser?, p1: DeserializationContext?): Trades {
        val node: JsonNode = jp?.codec?.readTree(jp)!!
        val tradesNode: ObjectNode = node.get("trades") as ObjectNode
        val tradeEntryList: List<TradeEntry> = tradesNode.get("data").asIterable()
                .map {
                    val propList: List<String> = it.asIterable().map { prop ->
                        prop.toString()
                    }
                    TradeEntry(tradeNo = propList[0].toLong(), tradeTime = LocalTime.parse(propList[1]), boardId = propList[2],
                            stockId = propList[3], price = BigDecimal.valueOf(propList[4].toDouble()), quantity = propList[5].toInt(),
                            value = propList[6].toDouble(), period = propList[7], tradeTimeGrp = propList[8].toInt(), sysTime = LocalDateTime.parse(propList[9]),
                            buySell = propList[10], decimals = propList[11].toInt())
                }.toList()

        val dataVersionNode: ObjectNode = node.get("dataversion") as ObjectNode
        val seqNum = dataVersionNode.get("data").asIterable().toList()[0][1].asInt()
        val version = dataVersionNode.get("data").asIterable().toList()[0][0].asInt()
        return Trades(trades = tradeEntryList, dataversion = DataVersion(version = version, seqNum = seqNum))
    }


}