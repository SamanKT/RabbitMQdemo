FROM maven:3.8.3-openjdk-17 as build-maven
WORKDIR /maven
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn install -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#RUN mkdir -p target/dependency
#RUN cd target/dependency
#RUN jar -xf target/*.jar

#FROM openjdk:17
#VOLUME /tmp
#ARG DEPENDENCY=/maven/target/dependency
#COPY --from=build-maven ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build-maven ${DEPENDENCY}/META-INF/lib /app/META-INF
#COPY --from=build-maven ${DEPENDENCY}/BOOT-INF/classes /app
#EXPOSE 8080
#ENTRYPOINT ["java", "-cp" , "app:app/lib/*", "com.rabbitmq.RabbitmqDemoApplication"]
FROM openjdk:17

COPY --from=build-maven maven/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"] 