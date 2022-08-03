# MockEbanking

[1] modules I added
1. Spring boot
2. Kafka
3. Spring.security
4. Springfox.Swagger
5. junit
6. postgresql

[2] tests I did
1. jwt 
   1. the jwt filter to authenticate the user
2. kafka
   1. tested zookeeper and kafka on windows
   2. experiments on producers and consumer
   3. pagination (this part I've not completed, just tested the case in postgresql)
3. logging
   1. used java.util.logging.LogManager to log into files.
   2. set level (default: info)
4. IntelliJ
   1. first time to use this IDE
5. Docker
   1. created the image by using the dockerfile.
6. CircleCI
   1. added config.yml 

[3] Problems not yet solved
1. How the kafka consumer filter the messages that come from producer
2. Adjust the CircleCI config 
