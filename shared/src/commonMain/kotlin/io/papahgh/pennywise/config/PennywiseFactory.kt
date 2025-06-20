package io.papahgh.pennywise.config

interface PennywiseFactory {
    fun createDatabase(): PennywiseDatabase
}

expect class DefaultPennywiseFactory : PennywiseFactory {
    override fun createDatabase(): PennywiseDatabase
}
