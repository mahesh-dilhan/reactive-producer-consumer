# Reactive Stream Kafka
Reactive Stream to consumer 


# Kafka Producer & Consumer
Examples in this repo:

 * External Service to push message to Producer
 * Consume data with HOT publisher 


## Setup & build

OpenJDK 13 (let jenv to manage multiple JDKs )

setup `zookeeper` and `kafka` in hardway. (Easy way is to use confluent binary). 
I used hardway for this setup to get hands on experience. 

### Start Zookeeper
```
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
```

### Start Kafka
```
kafka-server-start /usr/local/etc/kafka/server.properties
```

Run `mvn package` to build a single executable JAR file.

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.3.1.RELEASE)

2020-07-06 20:30:08.241  INFO 32162 --- [           main] c.r.r.ReactiveStreamProducerApplication  : Starting ReactiveStreamProducerApplication on Maheshs-MBP with PID 32162 (/Users/mahesh/play/stream/reactive-stream-producer/target/classes started by mahesh in /Users/mahesh/play/stream/reactive-stream-producer)
2020-07-06 20:30:08.245  INFO 32162 --- [           main] c.r.r.ReactiveStreamProducerApplication  : No active profile set, falling back to default profiles: default
2020-07-06 20:30:09.055  INFO 32162 --- [tive-group-id-1] o.a.k.clients.consumer.ConsumerConfig    : ConsumerConfig values: 
	allow.auto.create.topics = true
	auto.commit.interval.ms = 5000
	auto.offset.reset = latest
	bootstrap.servers = [localhost:9092]
	check.crcs = true
	client.dns.lookup = default
	client.id = reactive-client
	client.rack = 
	connections.max.idle.ms = 540000
	default.api.timeout.ms = 60000
	enable.auto.commit = false
	exclude.internal.topics = true
	fetch.max.bytes = 52428800
	fetch.max.wait.ms = 500
	fetch.min.bytes = 1
	group.id = reactive-group-id
	group.instance.id = null
	heartbeat.interval.ms = 3000
	interceptor.classes = []
	internal.leave.group.on.close = true
	isolation.level = read_uncommitted
	key.deserializer = class org.apache.kafka.common.serialization.LongDeserializer
	max.partition.fetch.bytes = 1048576
	max.poll.interval.ms = 300000
	max.poll.records = 500
	metadata.max.age.ms = 300000
	metric.reporters = []
	metrics.num.samples = 2
	metrics.recording.level = INFO
	metrics.sample.window.ms = 30000
	partition.assignment.strategy = [class org.apache.kafka.clients.consumer.RangeAssignor]
	receive.buffer.bytes = 65536
	reconnect.backoff.max.ms = 1000
	reconnect.backoff.ms = 50
	request.timeout.ms = 30000
	retry.backoff.ms = 100
	sasl.client.callback.handler.class = null
	sasl.jaas.config = null
	sasl.kerberos.kinit.cmd = /usr/bin/kinit
	sasl.kerberos.min.time.before.relogin = 60000
	sasl.kerberos.service.name = null
	sasl.kerberos.ticket.renew.jitter = 0.05
	sasl.kerberos.ticket.renew.window.factor = 0.8
	sasl.login.callback.handler.class = null
	sasl.login.class = null
	sasl.login.refresh.buffer.seconds = 300
	sasl.login.refresh.min.period.seconds = 60
	sasl.login.refresh.window.factor = 0.8
	sasl.login.refresh.window.jitter = 0.05
	sasl.mechanism = GSSAPI
	security.protocol = PLAINTEXT
	security.providers = null
	send.buffer.bytes = 131072
	session.timeout.ms = 10000
	ssl.cipher.suites = null
	ssl.enabled.protocols = [TLSv1.2]
	ssl.endpoint.identification.algorithm = https
	ssl.key.password = null
	ssl.keymanager.algorithm = SunX509
	ssl.keystore.location = null
	ssl.keystore.password = null
	ssl.keystore.type = JKS
	ssl.protocol = TLSv1.2
	ssl.provider = null
	ssl.secure.random.implementation = null
	ssl.trustmanager.algorithm = PKIX
	ssl.truststore.location = null
	ssl.truststore.password = null
	ssl.truststore.type = JKS
	value.deserializer = class com.reactivestream.reactivestreamproducer.PatientSerDes

2020-07-06 20:30:09.172  INFO 32162 --- [tive-group-id-1] o.a.kafka.common.utils.AppInfoParser     : Kafka version: 2.5.0
2020-07-06 20:30:09.174  INFO 32162 --- [tive-group-id-1] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 66563e712b0b9f84
2020-07-06 20:30:09.174  INFO 32162 --- [tive-group-id-1] o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1594038609171
2020-07-06 20:30:09.176  INFO 32162 --- [tive-group-id-1] o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=reactive-client, groupId=reactive-group-id] Subscribed to topic(s): rektopic
2020-07-06 20:30:09.448  INFO 32162 --- [tive-group-id-1] org.apache.kafka.clients.Metadata        : [Consumer clientId=reactive-client, groupId=reactive-group-id] Cluster ID: MehXd5CVQeOtxXQR6yCaWg
2020-07-06 20:30:09.449  INFO 32162 --- [tive-group-id-1] o.a.k.c.c.internals.AbstractCoordinator  : [Consumer clientId=reactive-client, groupId=reactive-group-id] Discovered group coordinator maheshs-mbp:9092 (id: 2147483647 rack: null)
2020-07-06 20:30:09.458  INFO 32162 --- [tive-group-id-1] o.a.k.c.c.internals.AbstractCoordinator  : [Consumer clientId=reactive-client, groupId=reactive-group-id] (Re-)joining group
2020-07-06 20:30:09.469  INFO 32162 --- [tive-group-id-1] o.a.k.c.c.internals.AbstractCoordinator  : [Consumer clientId=reactive-client, groupId=reactive-group-id] Join group failed with org.apache.kafka.common.errors.MemberIdRequiredException: The group member needs to have a valid member id before actually entering a consumer group
2020-07-06 20:30:09.470  INFO 32162 --- [tive-group-id-1] o.a.k.c.c.internals.AbstractCoordinator  : [Consumer clientId=reactive-client, groupId=reactive-group-id] (Re-)joining group
2020-07-06 20:30:09.474  INFO 32162 --- [tive-group-id-1] o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=reactive-client, groupId=reactive-group-id] Finished assignment for group at generation 4: {reactive-client-95958efd-a61d-4f90-9232-bf9a713b18ce=Assignment(partitions=[rektopic-0])}
2020-07-06 20:30:09.477  INFO 32162 --- [tive-group-id-1] o.a.k.c.c.internals.AbstractCoordinator  : [Consumer clientId=reactive-client, groupId=reactive-group-id] Successfully joined group with generation 4
2020-07-06 20:30:09.481  INFO 32162 --- [tive-group-id-1] o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=reactive-client, groupId=reactive-group-id] Adding newly assigned partitions: rektopic-0
2020-07-06 20:30:09.485  INFO 32162 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port(s): 9001
2020-07-06 20:30:09.485  INFO 32162 --- [tive-group-id-1] c.r.r.KafkaConfiguration                 : Group reactive-group-id partitions assigned [rektopic-0]
2020-07-06 20:30:09.492  INFO 32162 --- [tive-group-id-1] o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=reactive-client, groupId=reactive-group-id] Found no committed offset for partition rektopic-0
2020-07-06 20:30:09.498  INFO 32162 --- [           main] c.r.r.ReactiveStreamProducerApplication  : Started ReactiveStreamProducerApplication in 1.702 seconds (JVM running for 2.121)
2020-07-06 20:30:09.504  INFO 32162 --- [tive-group-id-1] o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=reactive-client, groupId=reactive-group-id] Resetting offset for partition rektopic-0 to offset 5936.

```

### Stream Output
Lets stream data for `IND` and `SG` and start monitor for server sent event. 
This simulates any subscriber can subscribe to any country and receieve events. 
But event are HOT publish event. 
Please refer projectreactor to understand HOT and COLD publisher.
```
Maheshs-MBP:~ mahesh$ curl localhost:9001/country/IND/stream

Maheshs-MBP:~ mahesh$ curl localhost:9001/country/SG/stream

```


### External source simulation 
Post Data to Kafka topic `rektopic` 
```
curl localhost:9001/patient -H "content-type: application/json" -d'{"id":"1","name":"Vicky","country":"IND"}'
curl localhost:9001/patient -H "content-type: application/json" -d'{"id":"3","name":"Ramesh","country":"IND"}'
curl localhost:9001/patient -H "content-type: application/json" -d'{"id":"4","name":"RVicktor","country":"IND"}'
curl localhost:9001/patient -H "content-type: application/json" -d'{"id":"1","name":"Thoma","country":"SG"}'
curl localhost:9001/patient -H "content-type: application/json" -d'{"id":"2","name":"Maria","country":"SG"}'
```

### Stream Output
Once data available `IND`
```
data:{"id":1,"name":"Vicky","country":"IND"}

data:{"id":3,"name":"Ramesh","country":"IND"}

data:{"id":4,"name":"RVicktor","country":"IND"}

```  

Once data available `SG`
```

data:{"id":1,"name":"Thoma","country":"SG"}

data:{"id":2,"name":"Maria","country":"SG"}

```