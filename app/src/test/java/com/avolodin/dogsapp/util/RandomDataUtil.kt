package com.avolodin.dogsapp.util

import java.util.*

object RandomDataUtil {

    fun generateString(): String = UUID.randomUUID().toString()
    fun generateInt(): Int = Random().nextInt()

}