package com.mpharma.testEngine

import io.vertx.core.AbstractVerticle
import io.vertx.ext.unit.report.ReportOptions
import io.vertx.ext.unit.TestOptions
import io.vertx.ext.unit.TestSuite
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.FlagField.after
import io.vertx.ext.unit.Async


class MainTest: AbstractVerticle() {

  override fun start() {
    val options = TestOptions().addReporter(ReportOptions().setTo("console"))
    val suite = TestSuite.create("io.vertx.example.unit.test.VertxUnitTest")

    suite.before { context ->
      vertx.createHttpServer().requestHandler { req -> req.response().end("Welcome to mpharma") }.listen(8080, context.asyncAssertSuccess())
    }

    suite.after { context -> vertx.close(context.asyncAssertSuccess()) }

  }
}