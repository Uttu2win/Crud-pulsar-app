package com.personal.crud_pulsar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.pulsar.annotation.EnablePulsar

@SpringBootApplication
@EnablePulsar
class CrudPulsarApplication

fun main(args: Array<String>) {
	runApplication<CrudPulsarApplication>(*args)
}
