package com.mpharma.testEngine.services.v1

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.ReturnDocument
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.mpharma.testEngine.models.Record
import com.mpharma.testEngine.services.AbstractService
import io.vertx.core.json.JsonObject
import org.bson.conversions.Bson
import org.litote.kmongo.*
import java.util.*

object RecordService : AbstractService() {

    private var collection: MongoCollection<Record>

    init {
        collection = MONGO_DB.getCollection<Record>("tbl_record")
    }

    /********
     * Add Record
     * @param record
     * @return
     */
    fun insert(record: Record) : MutableList<Record> {
        collection.insertOne(record)
        return findById(record.id.toString())
    }

    /********
     * Find record by ID
     * @param id
     * @return
     */
    fun findById(id: String) : MutableList<Record> {
        return collection.find(Record::id eq id).toMutableList()
    }

    /***********
     * Find By Code
     * @param code
     * @return
     */
    fun findbyCode(code:String): MutableList<Record> {
       return collection.find(Record::code eq code).toMutableList()
    }

    /************
     * Remove Record
     * @param code
     */
    fun removeRecord(code: String): DeleteResult? {
        return collection.deleteOne(Record::code eq code)
    }


    /***************
     * Update Records
     * @param details
     * @param code
     * @return
     */
    fun updateRecord(details:JsonObject,code:String): Record? {
        println("::: printing Details to update :::$details")
        val param =ArrayList<Bson>()
        if(!details.getString("category").isNullOrEmpty()){
            param.add(set(Record::category , details.getString("category")))
        }
        if(!details.getString("category_title").isNullOrEmpty()){
            param.add(set(Record::category_title , details.getString("category_title")))
        }
        if(!details.getString("diagnose_code").isNullOrEmpty()){
            param.add(set(Record::diagnose_code , details.getString("diagnose_code")))
        }
        if(!details.getString("description").isNullOrEmpty()){
            param.add(set(Record::description , details.getString("description")))
        }
        if(!details.getString("modified_by").isNullOrEmpty()){
            param.add(set(Record::modified_by ,details.getString("modified_by")) )
        }
        param.add(set(Record::modified_date , Date()) )
//        param.add(Record::code eq code)
//        println("$param ....>>>>>>")
        return collection.findOneAndUpdate(Record::code eq code, combine(param),FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER) )
    }

    /************
     * Get all Records
     * @param details
     * @return
     */
    fun getRecords(details:JsonObject): MutableList<Record> {
        var pageSize =20
        var pageNum =1
        var skips = pageSize *(pageNum-1)
        if(!details.getString("pageSize").isNullOrEmpty() || !details.getString("pageNum").isNullOrEmpty()){
            pageNum =details.getString("pageNum").toInt()
            pageSize =details.getString("pageSize").toInt()
            skips = pageSize *(pageNum-1)
            return collection.find().limit(pageSize).skip(skips).descendingSort(Record::created_date).toMutableList()
        }
        return collection.find().limit(pageSize).skip(skips).descendingSort(Record::created_date).toMutableList()
    }







}