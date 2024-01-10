package com.example.robot.reponse

import io.swagger.v3.oas.annotations.media.Schema

data class HttpResponse(
    @Schema(description = "The response description")
    val description : String,
    @Schema(description = "The response code")
    val code : String) {
}