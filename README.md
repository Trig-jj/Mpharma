# Mpharma

Test
#######################################
Development language :Kotlin (Vertx)
database: Mongo
#########################################
###########################################


this project was developed using Kotlin and framework is Vertx and kmongo as the ODM, its a maven project and can be compiled again using intellij or netbeans.
for database we only create database :- mpharma_db and collection:- tbl_record.
port , dbname can be changed and changes should also be done in the codes.


:
##################################################################################
Name:Add Record
Routes(POST): localhost:8080/api/v1/add

requestData:
{
	"apiKey" :"2222222",
	"details":{
		"category":"A0",
		"category_title":"Malignant neoplasm of anus and anal canal",
		"code":"12348",
		"diagnose_code":"A01234",
		"description":"Comma-induced anal retention",
		"addedBy":"jon"
	}
	
}

Response:
{
  "msg": "record add successfully",
  "data": [
    {
      "id": "5defaf6dd3554d26dc6a14d0",
      "category": "A0",
      "category_title": "Malignant neoplasm of anus and anal canal",
      "code": "12348",
      "diagnose_code": "A01234",
      "description": "Comma-induced anal retention",
      "addedBy": "jon",
      "created_date": 1575989101351,
      "modified_by": null,
      "modified_date": null
    }
  ],
  "code": "00"
}

#############################################################################################

Name :Update Record:
Route(POST) : localhost:8080/api/v1/update

requestData :
{
	"apiKey" :"2222222",
	"details":{
		"category":"A0",
		"category_title":"Malignant neoplasm of anus and anal canal...",
		"code":"12348",
		"diagnose_code":"A01234",
		"description":"Comma-induced anal retention ....",
		"modified_by":"jon"
	}
	
}

Response:
{
  "msg": "Record updated",
  "data": {
    "id": "5defaf6dd3554d26dc6a14d0",
    "category": "A0",
    "category_title": "Malignant neoplasm of anus and anal canal...",
    "code": "12348",
    "diagnose_code": "A01234",
    "description": "Comma-induced anal retention ....",
    "addedBy": "jon",
    "created_date": 1575989101351,
    "modified_by": "jon",
    "modified_date": 1576004742125
  },
  "code": "00"
}

NB:All fields can be updated except code which is a unique identifier

###############################################################################################

Name : Find One record
Route(POST) : localhost:8080/records

requestData :
{
	"code":"12345"
}

Response:
{
  "msg": "Record fetched successfully",
  "data": [
    {
      "id": "5defaf39d3554d26dc6a14cd",
      "category": "A0",
      "category_title": "Malignant neoplasm of anus and anal canal",
      "code": "12345",
      "diagnose_code": "A01234",
      "description": "Comma-induced anal retention",
      "addedBy": "jon",
      "created_date": 1575989049869,
      "modified_by": null,
      "modified_date": null
    }
  ],
  "code": "00"
}

#################################################################################################
Name : Get all Records
Route(POST):localhost:8080/records/all

NB: default page size is 20 and page Number is 1 

requestdata:
{
	"pageSize":"2",
	"pageNum":"1"
}

response:
{
  "msg": "Record fetched successfully",
  "data": [
    {
      "id": "5defaf6dd3554d26dc6a14d0",
      "category": "A0",
      "category_title": "Malignant neoplasm of anus and anal canal",
      "code": "12348",
      "diagnose_code": "A01234",
      "description": "Comma-induced anal retention",
      "addedBy": "jon",
      "created_date": 1575989101351,
      "modified_by": null,
      "modified_date": null
    },
    {
      "id": "5defaf49d3554d26dc6a14cf",
      "category": "A0",
      "category_title": "Malignant neoplasm of anus and anal canal",
      "code": "12347",
      "diagnose_code": "A01234",
      "description": "Comma-induced anal retention",
      "addedBy": "jon",
      "created_date": 1575989065509,
      "modified_by": null,
      "modified_date": null
    }
  ],
  "code": "00"
}

###############################################################
Name  :Delete Record
Route(POST) : localhost:8080/records/delete
 requestData:
 {
	"apiKey":"2222",
	"details":{
		"code":"12345"
	}
}

response:
{
  "msg": "record deleted",
  "code": "00"
}

#####################################################################



