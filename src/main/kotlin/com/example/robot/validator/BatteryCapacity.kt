package com.example.robot.validator

import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive


import jakarta.validation.Constraint
import kotlin.reflect.KClass
@NotNull(message = "The battery capacity cannot be null")
@Positive(message = "The battery capacity can only be positive")
@Max(value = 100, message = "The battery capacity cannot exceed 100%")
@Digits(fraction = 0, integer = 3, message = "The battery capacity must be integer")
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class BatteryCapacity(
    val message: String = "",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
