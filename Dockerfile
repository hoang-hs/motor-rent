FROM gradle:8.5-jdk21-alpine
ADD --chown=gradle . /code
WORKDIR /code
CMD ["gradle", "--stacktrace", "run"]