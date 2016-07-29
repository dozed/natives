

Contains sample code for Scala Native

  - `1-hello`: a hello world
  - `2-external`: external bindings
  - `3-library`: compile and use a Scala library with Scala Native


## Docker build container

The docker container can be used to build and run the sample code.
Make sure that Docker is installed in your host system.
Building and running the container might take a while (downloads the internet):

```
cd docker
docker build --tag scala-native-build .
docker run scala-native-build
```

The sample sources are contained in `/home/sources`, the Scala Native sources are contained in `/tmp/scala-native`.

To run a sample go to the directory and run SBT, e.g. for `1-hello`:

```
cd 1-hello
sbt -Dsun.jnu.encoding=UTF-8
> run
```
