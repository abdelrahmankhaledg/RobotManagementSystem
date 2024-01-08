package com.example.robot.validator.medication

import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive


import jakarta.validation.Constraint
import kotlin.reflect.KClass
@NotNull(message = "The medication weight cannot be null")
@Positive(message = "The medication weight can only be positive")
@Max(value = 500, message = "The medication weight cannot exceed 500gr")
@Digits(fraction = 0, integer = 3, message = "The medication weight must be integer")
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class MedicationWeight(
    val message: String = "",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
