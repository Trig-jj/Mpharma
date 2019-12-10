package com.mpharma.testEngine.services

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

abstract class AbstractService() {
    var MONGO_DB : MongoDatabase

    init {
        val mongoClient = KMongo.createClient(host = "127.0.0.1", port = 27017)
        MONGO_DB = mongoClient.getDatabase("mpharma_db")
    }

    fun getMongoDB(): MongoDatabase {
        return this.MONGO_DB
    }
}