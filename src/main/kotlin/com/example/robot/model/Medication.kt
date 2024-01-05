package com.example.robot.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "MEDICATION")
data class Medication(
    @Id
    @Column(name = "NAME")
    val name : String,

    @Column(name = "CODE")
    val code : String,

    @Column(name = "WEIGHT")
    val weight : Int,

    @Column(name = "IMAGE_URL")
    val imageUrl : String
)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Medication

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Medication(name='$name', code='$code', weight=$weight, imageUrl='$imageUrl')"
    }
}