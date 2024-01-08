package com.example.robot.validator.robot


import jakarta.validation.Constraint
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass
@NotNull(message = "Robot model cannot be null")
@Pattern(regexp = "^(LIGHTWEIGHT|MIDDLEWEIGHT|CRUISERWEIGHT|HEAVYWEIGHT)\$",
    message = "The robot model can only be one of LIGHTWEIGHT, MIDDLEWEIGHT," +
        "CRUISERWEIGHT OR HEAVYWEIGHT")
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class RobotModel(
    val message: String = "",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
