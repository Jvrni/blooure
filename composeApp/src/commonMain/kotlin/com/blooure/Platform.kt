package com.blooure

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform