FROM gradle:alpine
WORKDIR /code
ADD . /code
RUN ["./gradlew", "build", "--stacktrace"]
EXPOSE 4567
CMD ["./gradlew", "up"]