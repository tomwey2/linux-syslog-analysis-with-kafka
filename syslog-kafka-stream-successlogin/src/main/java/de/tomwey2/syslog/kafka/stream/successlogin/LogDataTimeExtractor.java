package de.tomwey2.syslog.kafka.stream.successlogin;

import de.tomwey2.syslog.kafka.data.SuccessLoginEvent;
import de.tomwey2.syslog.kafka.data.SuccessLoginEventFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

/**
 * Extracts the embedded timestamp of a record.
 * @see <a href="https://kafka.apache.org/21/documentation/streams/developer-guide/config-streams#timestamp-extractor">Configuring a stream application</a>
 */
public class LogDataTimeExtractor implements TimestampExtractor {
    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long partitionTime) {
        long timestampMillis = -1;
        final SuccessLoginEvent failedLoginEvent = SuccessLoginEventFactory.toSuccessLoginEvent((String) consumerRecord.value());
        if (failedLoginEvent != null) {
            timestampMillis = failedLoginEvent.getTimestampMillis();
        }
        if (timestampMillis < 0) {
            // fall back to wall-clock time (processing-time).
            timestampMillis = System.currentTimeMillis();
        }
        return timestampMillis;
    }
}
