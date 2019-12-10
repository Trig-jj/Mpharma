package com.mpharma.testEngine.models

import org.bson.codecs.pojo.annotations.BsonId
import java.util.Date

data class Record constructor(
        @BsonId val id: String?,
        val category:String?,
        val category_title:String?,
        val code :String,
        val diagnose_code:String?,
        val description:String?,
        val addedBy:String?,
        val created_date:Date,
        val modified_by:String?,
        val modified_date:Date?
)