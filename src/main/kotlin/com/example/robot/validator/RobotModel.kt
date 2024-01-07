package com.example.robot.validator


import jakarta.validation.Constraint
import org.intellij.lang.annotations.RegExp
import kotlin.reflect.KClass
@RegExp(prefix = "^(LIGHTWEIGHT|MIDDLEWEIGHT|CRUISERWEIGHT|HEAVYWEIGHT)\$")
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class RobotModel(
    val message: String = "The robot model can only be one of LIGHTWEIGHT, MIDDLEWEIGHT," +
            "CRUISERWEIGHT OR HEAVYWEIGHT",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
