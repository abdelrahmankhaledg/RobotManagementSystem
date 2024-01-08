package com.example.robot.validator.medication

import jakarta.validation.Constraint
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

@NotBlank(message = "The medication code cannot be empty")
@Pattern(regexp = "^[A-Z0-9_]+\$", message = "Medication code can only contain upper case letters, digits and _")
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class MedicationCode(
    val message: String = "",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)