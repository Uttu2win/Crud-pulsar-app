package com.personal.crud_pulsar

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import java.io.Serializable


@Entity
@Table(name = "\"User\"")
data class User(
    @Id
    val id: Long,
    val name: String,
    @field:Min(0, message = "Age cannot be negative")
    @field:Max(120, message = "Age cannot be greater than 120")
    val age: Int,
    val address: String,
    val phoneNumber: Long
) : Serializable
