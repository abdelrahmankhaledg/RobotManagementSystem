package com.example.robot.validator.robot


import jakarta.validation.Constraint
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Positive
import kotlin.reflect.KClass

@Positive(message = "The battery capacity can only be positive")
@Max(value = 100, message = "The battery capacity cannot exceed 100%")
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class BatteryCapacity(
    val message: String = "",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
