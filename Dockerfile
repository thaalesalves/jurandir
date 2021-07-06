FROM docker.io/alpine

RUN apk add -u openjdk11 git maven && \
    git clone https://github.com/thaalesalves/jurandir /opt/jurandir && \
    mvn clean install -f /opt/jurandir/pom.xml

ENTRYPOINT [ "java -jar /opt/jurandir/target/jurandir-1.0.0.jar" ]