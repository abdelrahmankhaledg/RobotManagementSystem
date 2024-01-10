package com.example.robot.reponse

import io.swagger.v3.oas.annotations.media.Schema

data class HttpResponseWithPayload (
    val httpResponse: HttpResponse,
    @Schema(description = "The data returned in the response if any")
    val payLoad : Any
){
}