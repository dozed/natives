# adapted from: https://github.com/scala-native/scala-native/blob/master/docker/Dockerfile

FROM ubuntu:16.04

ENV SCALA_VERSION 2.11.8
ENV SBT_VERSION 0.13.11

RUN apt-get update && \
    apt-get install -y \
    wget ping tar curl git \
    openjdk-8-jdk \
    clang++-3.7 llvm-3.7 llvm-3.7-dev llvm-3.7-runtime llvm-3.7-tool \
    libgc-dev libcurl4-openssl-dev

ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk

RUN \
  curl -fsL http://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | tar xfz - -C /root/ && \
  echo >> /root/.bashrc && \
  echo 'export PATH=~/scala-$SCALA_VERSION/bin:$PATH' >> /root/.bashrc

RUN \
  curl -L -o sbt-$SBT_VERSION.deb http://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb

RUN apt-get update && \
    apt-get -y install sbt

WORKDIR /tmp

RUN git clone https://github.com/scala-native/scala-native.git

WORKDIR /tmp/scala-native

RUN sbt compile

RUN sbt publishLocal

WORKDIR /home/sources

CMD ["/bin/bash"]
