FROM openjdk:11
COPY build/libs/jupiteropt-assessment-0.0.1-SNAPSHOT.jar jupiteropt-assessment.jar
ENTRYPOINT ["java","-jar","/jupiteropt-assessment.jar"]
