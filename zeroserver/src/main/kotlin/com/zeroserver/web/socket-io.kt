package com.zeroserver.web

import io.socket.client.IO
import io.socket.client.Socket
import mu.KLogging
import okhttp3.OkHttpClient
import okhttp3.internal.tls.OkHostnameVerifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.security.KeyStore
import javax.annotation.PostConstruct
import javax.net.ssl.TrustManagerFactory

@Configuration
class SocketIO{

    companion object: KLogging()



    @Bean
    fun socketIO() : Socket {
//       val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
//       trustManagerFactory.init(null as KeyStore)
//
//
//        val hostNameVerifier = OkHostnameVerifier.INSTANCE
//        val okHttpClient = OkHttpClient.Builder().hostnameVerifier(hostNameVerifier)
//                .sslSocketFactory()
//
         val socket = IO.socket("https://ws-api.iextrading.com/1.0/tops")
//        socket.on(Socket.EVENT_MESSAGE) {logger.info { it }}
//        socket.on(Socket.EVENT_CONNECT) {
//            socket.emit("subsribe", "snap,fb,aig+")
//        }
//        socket.on(Socket.EVENT_DISCONNECT){logger.info { "Disconnected" }}
       return socket
    }


}