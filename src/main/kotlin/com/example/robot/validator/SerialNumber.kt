package com.example.robot.validator


import jakarta.validation.Constraint
import jakarta.validation.constraints.Size
import kotlin.reflect.KClass

@Size(min = 1, max = 100, message = "The serial number must be between 1 and 100 characters long")
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class SerialNumber(
    val message: String = "The serial number must be between 1 and 100",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
