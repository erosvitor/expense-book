package com.ctseducare.expensebook.plugins

import com.ctseducare.expensebook.route.expense
import com.ctseducare.expensebook.route.status
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.kodein.di.DI

fun Application.configureRouting(di: DI) {
    routing {
        status()
        expense(di)
    }
}