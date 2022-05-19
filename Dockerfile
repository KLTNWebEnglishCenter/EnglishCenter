FROM openjdk:11
LABEL maintainer="englishcenter"
ADD target/EnglishCenter-0.0.1-SNAPSHOT.jar springboot-docker-englishcenter.jar
ENTRYPOINT ["java","-jar","springboot-docker-englishcenter.jar"]