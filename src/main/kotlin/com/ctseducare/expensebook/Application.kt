package com.ctseducare.expensebook

import com.ctseducare.expensebook.di.daoModule
import com.ctseducare.expensebook.di.databaseModule
import com.ctseducare.expensebook.di.serviceModule
import com.ctseducare.expensebook.plugins.configureRouting
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import org.kodein.di.DI

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    val di = DI {
        import(databaseModule)
        import(daoModule)
        import(serviceModule)
    }

    configureRouting(di)
}
