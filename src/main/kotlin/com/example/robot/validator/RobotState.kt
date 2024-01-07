package com.example.robot.validator


import jakarta.validation.Constraint
import org.intellij.lang.annotations.RegExp
import kotlin.reflect.KClass
@RegExp(prefix = "^(IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING)\$")
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class RobotState(
    val message: String = "The robot state can only be one of IDLE, LOADING, LOADED" +
            "DELIVERING, DELIVERED OR RETURNING",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
