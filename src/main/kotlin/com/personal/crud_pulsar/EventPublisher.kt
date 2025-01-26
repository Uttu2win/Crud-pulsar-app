package com.personal.crud_pulsar

import org.apache.pulsar.client.api.PulsarClientException
import org.springframework.beans.factory.annotation.Value
import org.springframework.pulsar.core.PulsarTemplate
import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory

@Service
class EventPublisher(
    private val template: PulsarTemplate<Any>
) {

    @Value("\${spring.pulsar.producer.topic-name1}")
    private lateinit var topicName1: String

    @Value("\${spring.pulsar.producer.topic-name2}")
    private lateinit var topicName2: String

    private val log = LoggerFactory.getLogger(EventPublisher::class.java)

    @Throws(PulsarClientException::class)
    fun publishPlainMessage(message: String) {
        template.send(topicName1, message)
        log.info("EventPublisher::publishPlainMessage published the event: {}", message)
    }

    @Throws(PulsarClientException::class)
    fun publishRawMessage(customer: Customer) {
        template.send(topicName2, customer)
        log.info("EventPublisher::publishRawMessage published the event: {}", customer.name)
    }
}
