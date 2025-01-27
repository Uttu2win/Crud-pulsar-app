package com.personal.crud_pulsar

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.pulsar.client.api.SubscriptionType
import org.apache.pulsar.common.schema.SchemaType
import org.springframework.pulsar.annotation.PulsarListener
import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory

@Service
class EventConsumer {

    private val log = LoggerFactory.getLogger(EventConsumer::class.java)
    private val objectMapper = jacksonObjectMapper()

    @PulsarListener(
        topics = ["\${spring.pulsar.producer.topic-name1}"],
        subscriptionName = "my-subscription",
        subscriptionType = [SubscriptionType.Shared]
    )
    fun consumeTextEvent(msg: String?) {
        log.info("EventConsumer::consumeTextEvent consumed events {}", msg)
    }

    @PulsarListener(
        topics = ["\${spring.pulsar.producer.topic-name2}"],
        subscriptionName = "my-subscription",
        schemaType = SchemaType.JSON,
        subscriptionType = [SubscriptionType.Shared]
    )
    fun consumeRawEvent(customer: Customer?) {
        customer?.let {
            if (customer.name.isBlank()) {
                throw RuntimeException("Invalid customer data, retrying...")
            }
            log.info("EventConsumer::consumeRawEvent consumed events {}", objectMapper.writeValueAsString(it))
        } ?: log.warn("Received null customer event")
    }

    @PulsarListener(
        topics = ["jt-raw-topic-dlq"],
        subscriptionName = "my-subscription-dlq",
        schemaType = SchemaType.JSON
    )
    fun handleDeadLetterEvent(customer: Customer?) {
        log.error("EventConsumer::handleDeadLetterEvent received dead-letter event: {}", customer)
    }

}
