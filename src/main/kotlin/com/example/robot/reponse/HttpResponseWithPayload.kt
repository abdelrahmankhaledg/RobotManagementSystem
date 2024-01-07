package com.example.robot.reponse

data class HttpResponseWithPayload (
    val httpResponse: HttpResponse,
    val payLoad : Any
){
}