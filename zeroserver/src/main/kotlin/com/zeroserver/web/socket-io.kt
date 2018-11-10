package com.zeroserver.web

import io.socket.client.IO
import io.socket.client.Socket
import mu.KLogging
import okhttp3.OkHttpClient
import okhttp3.internal.tls.OkHostnameVerifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream
import java.security.KeyStore
import java.util.*
import javax.net.ssl.*

@Configuration
class SocketIO {

    companion object : KLogging()


    @Bean
    fun sockIO(): Socket {
        logger.info { "Init Socket IO" }
        IO.setDefaultOkHttpWebSocketFactory(okHttpClient())
        IO.setDefaultOkHttpCallFactory(okHttpClient())
        val opts = IO.Options().apply {
            callFactory = okHttpClient()
            webSocketFactory = okHttpClient()
        }

        val socket = IO.socket("https://ws-api.iextrading.com/1.0/tops", opts)
        socket.on(Socket.EVENT_MESSAGE) {logger.info { it }}
        socket.on(Socket.EVENT_CONNECT) {
            socket.emit("subsribe", "aapl, snap,fb,aig+")
            socket.emit("unsubsribe", "snap", {logger.info { it }})
        }
        socket.on(Socket.EVENT_DISCONNECT){logger.info { "Disconnected" }}
        socket.open()
        return socket
    }

    @Bean
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().hostnameVerifier(OkHostnameVerifier.INSTANCE)
            .sslSocketFactory(sslSocketFactory(), X509TrustManager())
            .build()


    @Bean
    fun X509TrustManager(): X509TrustManager {
        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).apply {
            init(loadKeyStore())
        }
        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)
            throw  IllegalStateException("Unexpected default trust managers: ${Arrays.toString(trustManagers)}")
        return trustManagers[0] as X509TrustManager
    }

    @Bean
    fun sslSocketFactory(): SSLSocketFactory = SSLContext.getInstance("TLS")
            .apply {
                init(null, arrayOf(X509TrustManager()), null)
            }.socketFactory


    @Bean
    fun loadKeyStore(): KeyStore = KeyStore.getInstance("JKS")
            .apply {
                val pwdArray = "password".toCharArray()
                load(FileInputStream("ZeroKeyStore.jks"), pwdArray)
            }


}