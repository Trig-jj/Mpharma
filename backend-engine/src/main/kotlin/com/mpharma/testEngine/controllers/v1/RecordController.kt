package com.mpharma.testEngine.controllers.v1

import com.mpharma.testEngine.models.Record
import com.mpharma.testEngine.services.v1.RecordService
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import java.util.*

class RecordController {

    companion object {


        /************
         * Add Record
         * @param routingContext
         * @return
         */
        fun addRecord(routingContext: RoutingContext) {
            val body: JsonObject? = routingContext.bodyAsJson

            try {
                val apiKey = body!!.getString("apiKey")
                if (apiKey.isEmpty()) {
                    routingContext.response()
                            .putHeader("Content-Type", "application/json; charset=utf-8")
                            .end(Json.encodePrettily(hashMapOf("code" to "02", "msg" to "apiKey is missing")))
                } else {
                    print(":::Add Records ::: $body")
                    val checkCode = RecordService.findbyCode(body.getJsonObject("details").getString("code"))
                    println(checkCode)
                    if (checkCode.isEmpty()) {
                        val addRecord = Record(
                                null,
                                body.getJsonObject("details").getString("category"),
                                body.getJsonObject("details").getString("category_title"),
                                body.getJsonObject("details").getString("code"),
                                body.getJsonObject("details").getString("diagnose_code"),
                                body.getJsonObject("details").getString("description"),
                                body.getJsonObject("details").getString("addedBy"),
                                Date(),
                                null,
                                null
                        )
                        val newRecord = RecordService.insert(addRecord)
                        println("::Response from Adding Records:::\n$newRecord")
                        routingContext.response()
                                .putHeader("Content-Type", "application/json; charset=utf-8")
                                .end(Json.encodePrettily(hashMapOf("code" to "00", "msg" to "record add successfully", "data" to newRecord)))

                    } else {
                        routingContext.response()
                                .putHeader("Content-Type", "application/json; charset=utf-8")
                                .end(Json.encodePrettily(hashMapOf("code" to "01", "msg" to "Record code already exist.")))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                routingContext.response()
                        .putHeader("Content-Type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(hashMapOf("code" to "02", "msg" to e.localizedMessage)))
            }
        }


        /**********************
         * Update Records
         * @param routingContext
         * @return
         */
        fun updateRecord(routingContext: RoutingContext) {
            try {
                val body: JsonObject? = routingContext.bodyAsJson

                val apiKey = body!!.getString("apiKey")
                val category: String? = body.getJsonObject("details").getString("category")
                val category_title: String? = body.getJsonObject("details").getString("category_title")
                val code = body.getJsonObject("details").getString("code")
                val diagnose_code = body.getJsonObject("details").getString("diagnose_code")
                val description = body.getJsonObject("details").getString("description")
                val modified_by = body.getJsonObject("details").getString("modified_by")
                if (apiKey.isEmpty()) {
                    routingContext.response()
                            .putHeader("Content-Type", "application/json; charset=utf-8")
                            .end(Json.encodePrettily(hashMapOf("code" to "02", "msg" to "apiKey is missing")))
                } else {
                    println(":::Update Records:::\n$body")
                    val updateRecord = JsonObject().put("category", category)
                            .put("category_title", category_title)
                            .put("diagnose_code", diagnose_code)
                            .put("description", description)
                            .put("modified_by", modified_by)
                    val setUpdate = RecordService.updateRecord(updateRecord, code)
                    println(":::Response from adding Body:::\n$setUpdate")
                    if (setUpdate !=null) {
                        routingContext.response()
                                .putHeader("Content-Type", "application/json; charset=utf-8")
                                .end(Json.encodePrettily(hashMapOf("code" to "00", "msg" to "Record updated", "data" to setUpdate)))
                    } else {
                        routingContext.response()
                                .putHeader("Content-Type", "application/json; charset=utf-8")
                                .end(Json.encodePrettily(hashMapOf("code" to "01", "msg" to "Record update failed")))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                routingContext.response()
                        .putHeader("Content-Type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(hashMapOf("code" to "02", "msg" to e.localizedMessage)))
            }
        }


        /************
         * Get all Records
         * @param routingContext
         * @return
         */
        fun getAllRecords(routingContext: RoutingContext) = try {
            val body = routingContext.bodyAsJson
            val pageSize: String? =  body.getString("pageSize")
            val pageNum: String? = body.getString("pageNum")
            val param = JsonObject().put("pageSize", pageSize).put("pageNum", pageNum)
            println("Get All Records:::\n$param ")
            val viewRecord = RecordService.getRecords(param)
            println("Get All Records Response:::\n$viewRecord ")
            if (viewRecord.isNotEmpty()){
                routingContext.response()
                        .putHeader("Content-Type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(hashMapOf("code" to "00", "msg" to "Record fetched successfully","data" to viewRecord)))
            }else{
                routingContext.response()
                        .putHeader("Content-Type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(hashMapOf("code" to "01", "msg" to "No data found")))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            routingContext.response()
                    .putHeader("Content-Type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(hashMapOf("code" to "02", "msg" to e.localizedMessage)))
        }


        /**************
         * Get a Record
         * @param routingContext
         * @return
         */
        fun getRecords(routingContext: RoutingContext){
            try{
                val body = routingContext.bodyAsJson
                val code = body.getString("code")
                println(":::Find Records by Code:::\n$body")
                val findRecord = RecordService.findbyCode(code)
                println(":::Find Records by Code Response:::\n$findRecord")
                if (findRecord.isNotEmpty()){
                    routingContext.response()
                            .putHeader("Content-Type", "application/json; charset=utf-8")
                            .end(Json.encodePrettily(hashMapOf("code" to "00", "msg" to "Record fetched successfully","data" to findRecord)))
                }else{
                    routingContext.response()
                            .putHeader("Content-Type", "application/json; charset=utf-8")
                            .end(Json.encodePrettily(hashMapOf("code" to "01", "msg" to "No data found")))
                }
            }catch (e:Exception){
                e.printStackTrace()
                routingContext.response()
                        .putHeader("Content-Type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(hashMapOf("code" to "02", "msg" to e.localizedMessage)))
            }
        }


        /****************
         * Delete Records
         * @param routingContext
         * @return
         */
        fun deleteRecord(routingContext: RoutingContext){
            try{
                val body = routingContext.bodyAsJson
                val apiKey = body!!.getString("apiKey")
                if (apiKey.isEmpty()){
                    routingContext.response()
                            .putHeader("Content-Type", "application/json; charset=utf-8")
                            .end(Json.encodePrettily(hashMapOf("code" to "02", "msg" to "apiKey is missing")))
                }else{
                    val code = body.getJsonObject("details").getString("code")
                    println("::Delete Records:::\n$code")
                    val deleteRecord =RecordService.removeRecord(code)
                    println(":::Delete Record Response:::\n$deleteRecord")
                    routingContext.response()
                            .putHeader("Content-Type", "application/json; charset=utf-8")
                            .end(Json.encodePrettily(hashMapOf("code" to "00", "msg" to "record deleted")))
                }
            }catch (e:Exception){
                e.printStackTrace()
                routingContext.response()
                        .putHeader("Content-Type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(hashMapOf("code" to "02", "msg" to e.localizedMessage)))
            }
        }
    }
}