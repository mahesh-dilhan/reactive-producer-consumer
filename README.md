# Cloud Stream Kafka
Eventual Consistency


# Kafka Producer
Examples in this repo:

 * Kafka Message Producer
 * Post Message to Kafka via Spring cloud stream


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

2020-07-02 20:35:59.534  INFO 58553 --- [           main] c.r.r.ReactiveStreamProducerApplication  : Starting ReactiveStreamProducerApplication on Maheshs-MBP with PID 58553 (/Users/mahesh/play/stream/reactive-stream-producer/target/classes started by mahesh in /Users/mahesh/play/stream/reactive-stream-producer)
2020-07-02 20:35:59.536  INFO 58553 --- [           main] c.r.r.ReactiveStreamProducerApplication  : No active profile set, falling back to default profiles: default
2020-07-02 20:36:00.221  INFO 58553 --- [           main] faultConfiguringBeanFactoryPostProcessor : No bean named 'errorChannel' has been explicitly defined. Therefore, a default PublishSubscribeChannel will be created.
2020-07-02 20:36:00.228  INFO 58553 --- [           main] faultConfiguringBeanFactoryPostProcessor : No bean named 'taskScheduler' has been explicitly defined. Therefore, a default ThreadPoolTaskScheduler will be created.
2020-07-02 20:36:00.234  INFO 58553 --- [           main] faultConfiguringBeanFactoryPostProcessor : No bean named 'integrationHeaderChannelRegistry' has been explicitly defined. Therefore, a default DefaultHeaderChannelRegistry will be created.
2020-07-02 20:36:00.284  INFO 58553 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'integrationChannelResolver' of type [org.springframework.integration.support.channel.BeanFactoryChannelResolver] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2020-07-02 20:36:00.288  INFO 58553 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'integrationDisposableAutoCreatedBeans' of type [org.springframework.integration.config.annotation.Disposables] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2020-07-02 20:36:00.297  INFO 58553 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.integration.config.IntegrationManagementConfiguration' of type [org.springframework.integration.config.IntegrationManagementConfiguration] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2020-07-02 20:36:01.081  INFO 58553 --- [           main] o.s.s.c.ThreadPoolTaskScheduler          : Initializing ExecutorService 'taskScheduler'
2020-07-02 20:36:01.095  INFO 58553 --- [           main] onConfiguration$FunctionBindingRegistrar : Functional binding is disabled due to the presense of @EnableBinding annotation in your configuration
2020-07-02 20:36:01.183  INFO 58553 --- [           main] o.s.i.endpoint.EventDrivenConsumer       : Adding {logging-channel-adapter:_org.springframework.integration.errorLogger} as a subscriber to the 'errorChannel' channel
2020-07-02 20:36:01.183  INFO 58553 --- [           main] o.s.i.channel.PublishSubscribeChannel    : Channel 'application.errorChannel' has 1 subscriber(s).
2020-07-02 20:36:01.183  INFO 58553 --- [           main] o.s.i.endpoint.EventDrivenConsumer       : started bean '_org.springframework.integration.errorLogger'
2020-07-02 20:36:01.184  INFO 58553 --- [           main] o.s.c.s.binder.DefaultBinderFactory      : Creating binder: kafka
2020-07-02 20:36:01.314  INFO 58553 --- [           main] o.s.c.s.binder.DefaultBinderFactory      : Caching the binder: kafka
2020-07-02 20:36:01.315  INFO 58553 --- [           main] o.s.c.s.binder.DefaultBinderFactory      : Retrieving cached binder: kafka
2020-07-02 20:36:01.389  INFO 58553 --- [           main] o.s.c.s.b.k.p.KafkaTopicProvisioner      : Using kafka topic for outbound: ktopic
2020-07-02 20:36:01.392  INFO 58553 --- [           main] o.a.k.clients.admin.AdminClientConfig    : AdminClientConfig values: 
	bootstrap.servers = [localhost:9092]
	client.dns.lookup = default
	client.id = 
	connections.max.idle.ms = 300000
	default.api.timeout.ms = 60000
	metadata.max.age.ms = 300000
	metric.reporters = []
	metrics.num.samples = 2
	metrics.recording.level = INFO
	metrics.sample.window.ms = 30000
	receive.buffer.bytes = 65536
	reconnect.backoff.max.ms = 1000
	reconnect.backoff.ms = 50
	request.timeout.ms = 30000
	retries = 2147483647
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

2020-07-02 20:36:01.555  INFO 58553 --- [           main] o.a.kafka.common.utils.AppInfoParser     : Kafka version: 2.5.0
2020-07-02 20:36:01.558  INFO 58553 --- [           main] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 66563e712b0b9f84
2020-07-02 20:36:01.558  INFO 58553 --- [           main] o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1593693361552
2020-07-02 20:36:02.122  INFO 58553 --- [           main] o.a.k.clients.producer.ProducerConfig    : ProducerConfig values: 
	acks = 1
	batch.size = 16384
	bootstrap.servers = [localhost:9092]
	buffer.memory = 33554432
	client.dns.lookup = default
	client.id = producer-1
	compression.type = none
	connections.max.idle.ms = 540000
	delivery.timeout.ms = 120000
	enable.idempotence = false
	interceptor.classes = []
	key.serializer = class org.apache.kafka.common.serialization.ByteArraySerializer
	linger.ms = 0
	max.block.ms = 60000
	max.in.flight.requests.per.connection = 5
	max.request.size = 1048576
	metadata.max.age.ms = 300000
	metadata.max.idle.ms = 300000
	metric.reporters = []
	metrics.num.samples = 2
	metrics.recording.level = INFO
	metrics.sample.window.ms = 30000
	partitioner.class = class org.apache.kafka.clients.producer.internals.DefaultPartitioner
	receive.buffer.bytes = 32768
	reconnect.backoff.max.ms = 1000
	reconnect.backoff.ms = 50
	request.timeout.ms = 30000
	retries = 0
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
	transaction.timeout.ms = 60000
	transactional.id = null
	value.serializer = class org.apache.kafka.common.serialization.ByteArraySerializer

2020-07-02 20:36:02.138  INFO 58553 --- [           main] o.a.kafka.common.utils.AppInfoParser     : Kafka version: 2.5.0
2020-07-02 20:36:02.138  INFO 58553 --- [           main] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 66563e712b0b9f84
2020-07-02 20:36:02.138  INFO 58553 --- [           main] o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1593693362138
2020-07-02 20:36:02.143  INFO 58553 --- [ad | producer-1] org.apache.kafka.clients.Metadata        : [Producer clientId=producer-1] Cluster ID: MehXd5CVQeOtxXQR6yCaWg
2020-07-02 20:36:02.144  INFO 58553 --- [           main] o.a.k.clients.producer.KafkaProducer     : [Producer clientId=producer-1] Closing the Kafka producer with timeoutMillis = 30000 ms.
2020-07-02 20:36:02.153  INFO 58553 --- [           main] o.s.c.s.m.DirectWithAttributesChannel    : Channel 'application.output' has 1 subscriber(s).
2020-07-02 20:36:02.272  INFO 58553 --- [           main] o.s.b.web.embedded.netty.NettyWebServer  : Netty started on port(s): 8080
2020-07-02 20:36:02.283  INFO 58553 --- [           main] c.r.r.ReactiveStreamProducerApplication  : Started ReactiveStreamProducerApplication in 3.096 seconds (JVM running for 3.472)

```

### Define Kafka Topic in yml file
```yaml
spring:
  cloud:
    stream:
      bindings:
        output:
          binder: kafka
          destination: ktopic
          group: output-group-1
          content-type: application/json
```

### Post Data
Post Data to endpoint `person` 
```
Maheshs-MBP:reactive-stream-producer mahesh$ curl localhost:8080/person -H "content-type: application/json" -d'{"name":"Shawn"}'
Maheshs-MBP:reactive-stream-producer mahesh$ curl localhost:8080/person -H "content-type: application/json" -d'{"name":"Mahesh"}'
Maheshs-MBP:reactive-stream-producer mahesh$ curl localhost:8080/person -H "content-type: application/json" -d'{"name":"Xiaoyuan"}'
```

### Please setup stream consumer to see the output  