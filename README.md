# ESI-2023-BE

Initialize Kafka listener for bookingCreatedTopic

    docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server broker:9092  --topic bookingCreatedTopic --from-beginning