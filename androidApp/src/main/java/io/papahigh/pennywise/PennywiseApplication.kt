package io.papahigh.pennywise

import android.app.Application
import io.papahgh.pennywise.config.PennywiseContext
import org.koin.android.ext.koin.androidContext

class PennywiseApplication : Application() {
    lateinit var context: PennywiseContext

    override fun onCreate() {
        super.onCreate()
        context =
            PennywiseContext.start {
                androidContext(this@PennywiseApplication)
            }
    }
}
