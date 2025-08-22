package org.example.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.example.event.OrderCancelledEvent;
import org.example.event.OrderCreatedEvent;
import org.example.event.PaymentReceivedEvent;
import org.example.event.ShippingScheduledEvent;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,       // use the "name" to decide subclass
        include = JsonTypeInfo.As.PROPERTY,
        property = "eventType"            // look at "eventType" field in JSON
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderCreatedEvent.class, name = "ORDER_CREATED"),
        @JsonSubTypes.Type(value = PaymentReceivedEvent.class, name = "PAYMENT_RECEIVED"),
        @JsonSubTypes.Type(value = ShippingScheduledEvent.class, name = "SHIPPING_SCHEDULED"),
        @JsonSubTypes.Type(value = OrderCancelledEvent.class, name = "ORDER_CANCELLED")
})
@Data
public abstract class BaseEvent {
    private String eventType;
}
