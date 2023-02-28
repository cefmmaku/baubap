package com.carlosFrias.baubapTest

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

// A custom runner to set up the instrumented application class for tests.
class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader,
        className: String,
        context: Context
    ): Application = Instrumentation.newApplication(HiltTestApplication::class.java, context)
}


