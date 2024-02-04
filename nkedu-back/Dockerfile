FROM openjdk:17-alpine

WORKDIR /workspace

ARG JAR_PATH=./build/libs

COPY ${JAR_PATH}/nkedu-back-0.0.1.jar ${JAR_PATH}/nkedu-back-0.0.1.jar

CMD ["java", "-jar", "./build/libs/nkedu-back-0.0.1.jar"]
