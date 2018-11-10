package com.zeroserver.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/stock"])
class ZeroController {


    @GetMapping(path = ["/info"] , produces = [])
    fun getAllCompanyInfo(){

    }



}