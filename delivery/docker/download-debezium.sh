#!/bin/bash
wget https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/1.5.4.Final/debezium-connector-mysql-1.5.4.Final-plugin.tar.gz
wget https://d2p6pa21dvn84.cloudfront.net/api/plugins/confluentinc/kafka-connect-jdbc/versions/10.7.6/confluentinc-kafka-connect-jdbc-10.7.6.zip

mkdir -p ./debezium-connector-mysql
tar -zxvf debezium-connector-mysql-1.5.4.Final-plugin.tar.gz debezium-connector-mysql/
unzip confluentinc-kafka-connect-jdbc-10.7.6.zip -d debezium-connector-mysql/
