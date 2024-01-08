package com.example.robot.validator.robot

import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive


import jakarta.validation.Constraint
import kotlin.reflect.KClass
@NotNull(message = "The weight limit cannot be null")
@Positive(message = "The weight limit can only be positive")
@Max(value = 500, message = "The weight limit cannot exceed 500gr")
@Digits(fraction = 0, integer = 3, message = "The weight limit must be integer")
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class WeightLimit(
    val message: String = "",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
