# WebSocket-With-Spring-Boot
WebSocket application with Spring Boot.

## What is WebSocket?
Web sockets are defined as a two-way communication protocol over a single TCP between the servers and the clients, which mean both the parties, communicate and exchange data at the same time. This protocol defines a full duplex communication from the ground up.
Using WebSockets is a good way to handle high scale data transfers between server-clients.

## How to Run?

```
docker pull wannaberoot/web-socket-with-spring-boot
docker run -p 8080:8080 web-socket-with-spring-boot
```

## After Running
1. After running the application, you can go to the Swagger.
```
http://localhost:8080/swagger-ui/index.html
```

2. Please give the path as "/v3/api-docs" instead of "petstore/*".

3. You can use the following login information for Rest APIs:
```
Username: admin
Password: admin
```

4. Before making a service request, use should go to following url for browser client and connect with a phone number.
```
http://localhost:8080/
```
![---](/images/image1.png)

5. After connection, you can now receive notifications by making service request.

## Example
1. After making some calls by /api/makeCall, we can use /api/getMissedCalls to get missed calls informations.
![---](/images/image2.png)
Note: Default language is "tr", you can type "us" if you want to get notifications in English.

2. Now, we can see notifications on browser client.

In Turkish

![---](/images/image3.png)

In English

![---](/images/image4.png)

3. Also using /api/sendDeliveryReport, callers can also receive notifications in both Turkish and English that you are available.

![---](/images/image5.png)

![---](/images/image6.png)

Note: Also you can review notifications from client by using console.

![---](/images/image7.png)
