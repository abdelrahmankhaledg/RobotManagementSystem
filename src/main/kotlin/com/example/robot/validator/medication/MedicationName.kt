package com.example.robot.validator.medication


import jakarta.validation.Constraint
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass
@NotBlank(message = "The medication name cannot be empty")
@Pattern(regexp = "^[a-zA-Z0-9_-]+\$", message = "Medication name can only contain letters, digits, - and _")
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class MedicationName(
    val message: String = "",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
