package com.zeroserver

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.io.FileInputStream

import java.io.FileOutputStream
import java.security.KeyStore

@RunWith(SpringRunner::class)
@SpringBootTest
class ZeroserverApplicationTests {

    @Test
    fun createKeyStore() {
        val ks = KeyStore.getInstance(KeyStore.getDefaultType())
        val pwdArray = "password".toCharArray()
        ks.load(null, pwdArray)
        FileOutputStream("ZeroKeyStore.jks").use {
           ks.store ( it, pwdArray)
        }
    }


    @Test
    fun setEntry() {

    }
    @Test
    fun loadKeyStore() {
        val ks = KeyStore.getInstance("JKS")
        val pwdArray = "password".toCharArray()
        ks.load(FileInputStream("ZeroKeyStore.jks"),pwdArray )

    }
}
