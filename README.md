# ESI-2023-BE

Initialize Kafka listener for bookingTopic

    docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092  --topic bookingTopic --from-beginning

Initialize Kafka listener for paymentTopic

    docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092  --topic paymentTopic --from-beginning

Initialize Kafka listener for checkingTopic

    docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092  --topic checkingTopic --from-beginning