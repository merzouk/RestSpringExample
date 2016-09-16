# RestSpringExample
use Rest with Spring

This example expose using Rest with Spring.

The repository contains the tools access data base.

You create your data base by sql file.

This part contains the unit test access to data base, I used hsql to test data base.

The service contains service

The person is the war that exposes the service Rest.

in Directory RestSpring, you run : mvn clean install -DskipTests

You deploy the war in your server, the unit test for Rest Service is PersonControllerClientTest.java

Set port value in REST_SERVICE_URI.

You can test you service Rest by Browser, the uris is in call_service file.
