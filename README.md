# submarine-events

## Getting Start
submarine-events is a lightweight, event-driven micro-service framework. It provides a common solution to receive events transported from producer and consume them via RESTful HTTP API. Event producers can send events to submarine-events via Kafka or submarine-events API. The event data must be described in a common format defined by CloudEvents protocol, which allows data exchange between different systems. submarine-events listens to events, then routes them to specific destinations via RESTful HTTP API configured by user. 

## Features
- Event-driven architecture, connecting event producers and consumers in a decoupled fashion

- CloudEvents protocol, achieving interoperability across different systems

- Dynamic configuration, eliminating the need of redeployment

- Sync or async mode, providing flexibility to fit different tasks

## How To Use

#### 1.Configure Kafka

submarine-events can receive event via both Kafka and submarine-events API. If you choose Kafka, configure Kafka as following. Otherwise, skip this step.
```
# /src/main/resources/bootstrap.yml
spring:
  cloud:
    stream:
      default-binder: kafka  
      kafka:
        binder:
          brokers: localhost:9092 #your kafka server address 
      bindings:
        input:
          destination: my_topic #your topic
          group: my_topic_group1 #consumer group of your topic 
          consumer:
            concurrency: 4 #concurrent consumer count, default is 1, less than or equal to partition count
            max-attempts: 5 #max retry count, default is 3
      function: 
        definition: restAPIConsume #function to consume message, already implemented stream/handler/EventHandler.java
```

#### 2.Configure Event Adapter Rules

##### I) Local Event Adapter Rules
```
# /src/main/resources/bootstrap.yml
event-adapter-rules:
  rule[0]:
    type: Demo #event type
    targetUri: http://localhost:8080/v1/demo/toUpperCase #RESTful HTTP API where event is routed to, here is a demo API implemented in demo/TargetUriDemo.java
    httpMethod: GET #method of RESTful HTTP API
    requestKeyMap: '{"oriReqName1": "convertedReqName1", "oriReqName2": "convertedReqName2"}' #request key map, not required
    responseKeyMap: '{"oriRespName1": "convertedRespName1", "oriRespName2":"convertedRespName2"}' #response key map, not required
  #rule[1]: more rules can be added here
```

##### II) Dynamic Event Adapter Rules via Nacos

Event adapter rules can also be configured dynamically in Nacos if available.

###### a) Configure Nacos
```
# /src/main/resources/bootstrap.yml
spring:
  application:
    name: submarine-events
  cloud:
    nacos:
      config:
        server-addr: localhost:80 #your nacos server address
        file-extension: yaml
```

###### b) Configure Event Adapter Rules in Nacos
```
# Nacos/submarine-events.yaml
event-adapter-rules:
  rule[0]: ...
  rule[1]: ...
...
```

#### 3.Send Event

Send an event defined by CloudEvents protocol to submarine-events via 1) the specific Kafka topic you configured or 2) submarine-events API: http://localhost:8080/v1/event/send.

Here is a demo event:
```
{
  "type":"Demo",
  "data": {
    "oriReqName1":"cat",
    "oriReqName2":"dog"
  },
  "id":"000000001",
  "source":"/event/source"
}
```
Learn more about [CloudEvents](https://github.com/cloudevents/spec).

#### 4.Route event to RESTful HTTP API

submarine-events finds corresponding event adapter rule according to `"type"`:
```
event-adapter-rules:
  rule[0]:
    type: Demo #event type
    targetUri: http://localhost:8080/v1/demo/toUpperCase #RESTful HTTP API where event is routed to, here is a demo API implemented in demo/TargetUriDemo.java
    httpMethod: GET #method of RESTful HTTP API
    requestKeyMap: '{"oriReqName1": "convertedReqName1", "oriReqName2": "convertedReqName2"}' #request key map, not required
    responseKeyMap: '{"oriRespName1": "convertedRespName1", "oriRespName2":"convertedRespName2"}' #response key map, not required
```
submarine-events calls http://localhost:8080/v1/demo/toUpperCase?convertedReqName1=cat&convertedReqName2=dog, uri is from `targetUri` and request parameter is converted from `"data"` by `requestKeyMap`, you can get response converted by `"responseKeyMap"`:
```
{
  "convertedRespName1": "CAT",
  "convertedRespName2": "DOG"
 }
```

## Contributing
First and foremost, thank you! We appreciate that you want to contribute to submarine-events, your time is valuable, and your contributions mean a lot to us.
There are many ways to contribute, including the following:
- Creating an issue
- Updating or correcting documentation
- Feature requests
- Bug reports
