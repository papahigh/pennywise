package io.papahgh.pennywise

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
