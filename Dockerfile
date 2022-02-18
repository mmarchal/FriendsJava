FROM arm32v7/openjdk

RUN apt-get update && apt-get install maven -y

RUN mkdir /app

WORKDIR ./app

COPY . .
COPY /src/main/resources/application.properties .

RUN mvn package

EXPOSE 20000

ENTRYPOINT ["java","-jar","/app/target/back-0.0.1-SNAPSHOT.jar"]