package com.ctseducare.expensebook.route

import com.ctseducare.expensebook.exception.DatabaseException
import com.ctseducare.expensebook.exception.ResourceNotFoundException
import com.ctseducare.expensebook.model.Expense
import com.ctseducare.expensebook.service.ExpenseService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.kodein.di.DI
import org.kodein.di.instance

fun Route.expense(di: DI) {

    val service by di.instance<ExpenseService>()

    route("/expense") {
        post {
            val request = call.receive<Expense>()
            try {
                val response = service.insert(request)
                call.respond(HttpStatusCode.OK, response)
            } catch (e: DatabaseException) {
                call.respond(HttpStatusCode.InternalServerError, e.message)
            }
        }
        get {
            val response = service.findAll()
            call.respond(HttpStatusCode.OK, response)
        }
        put {
            val request = call.receive<Expense>()
            try {
                val response = service.update(request)
                call.respond(HttpStatusCode.OK, response)
            } catch (e1: ResourceNotFoundException) {
                call.respond(HttpStatusCode.NotFound, e1.message)
            } catch (e2: DatabaseException) {
                call.respond(HttpStatusCode.InternalServerError, e2.message)
            }
        }
        route("/{id}") {
            delete {
                val id = call.parameters["id"]?.toInt() ?: 0
                try {
                    val response = service.delete(id)
                    call.respond(HttpStatusCode.OK, response)
                } catch (e1: ResourceNotFoundException) {
                    call.respond(HttpStatusCode.NotFound, e1.message)
                } catch (e2: DatabaseException) {
                    call.respond(HttpStatusCode.InternalServerError, e2.message)
                }
            }
            get {
                val id = call.parameters["id"]?.toInt() ?: 0
                try {
                    val response = service.findById(id)
                    call.respond(HttpStatusCode.OK, response)
                } catch (e1: ResourceNotFoundException) {
                    call.respond(HttpStatusCode.NotFound, e1.message)
                } catch (e2: DatabaseException) {
                    call.respond(HttpStatusCode.InternalServerError, e2.message)
                }
            }
        }
    }

}