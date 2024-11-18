FROM maven:3.9.9-amazoncorretto-17-alpine AS builder
WORKDIR /sources
COPY /pom.xml /sources/
RUN mvn -B -e -q -Dspring-boot.repackage.skip=true dependency:go-offline
COPY /src/ /sources/src/
RUN mvn clean verify -B -e -q -DskipTests \
    && cp /sources/target/*.jar /sources/application.jar \
    && java -Djarmode=layertools -jar /sources/application.jar extract

FROM amazoncorretto:17-alpine
WORKDIR /delivery
COPY --from=builder /sources/dependencies/ /delivery/
COPY --from=builder /sources/spring-boot-loader/ /delivery/
COPY --from=builder /sources/snapshot-dependencies/ /delivery/
COPY --from=builder /sources/application/ /delivery/

EXPOSE 8080
ENTRYPOINT ["java", "-cp", "/delivery/BOOT-INF/classes:/delivery/BOOT-INF/lib/*", \
            "com.jel.delivery.DeliveryApplication"]
