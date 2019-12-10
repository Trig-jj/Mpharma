package com.mpharma.testEngine

import com.mpharma.testEngine.controllers.v1.RecordController
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.core.Vertx



class Main : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {
        val router = Router.router(vertx)

        router.route().handler(BodyHandler.create())

        router.get("/").handler(this::index)

        router.post("/api/v1/add").handler { req ->
            RecordController.addRecord(req)
        }
        router.post("/api/v1/update").handler { req ->
            RecordController.updateRecord(req)
        }
        router.post("/records/all").handler { req ->
            RecordController.getAllRecords(req)
        }
        router.post("/records").handler { req ->
            RecordController.getRecords(req)
        }
        router.post("/records/delete").handler { req ->
            RecordController.deleteRecord(req)
        }



        vertx.createHttpServer()
                .requestHandler { router.accept(it) }
                .listen(config().getInteger("http.port", 8080)) { result ->
                    if (result.succeeded()) {
                        startFuture.complete()
                        println("Application Started!")
                    } else {
                        startFuture.fail(result.cause())
                        println("Application Failed!!!")
                    }
                }
    }

    // Handlers
    private fun index(req: RoutingContext) {
        println("BODY: [${req.bodyAsString}]")
        println("Received ${req.request().rawMethod()} request at route /")
        req.response().end("Welcome to Mpharma!")
    }
}


