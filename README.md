# thorntail-issue
## First Build and Install the Example Dependency
```
$ mvn -f needs-guava clean install
```
## Then Build and Run the Working Example
```
$ mvn -f guava-fail clean package
$ java -jar guava-fail/target/guava-fail-thorntail.jar
```
This should startup successfully, but the app doesn't do anything so there's nothing to test.
```
INFO  [org.wildfly.swarm] (main) THORN99999: Thorntail is Ready
```
## Then Build and Run the Failing Example
```
$ mvn -f guava-fail -P with-dse clean package
$ java -jar guava-fail/target/guava-fail-thorntail.jar
```
This should fail with the following root cause:
```
ERROR [stderr] (main) WELD-001474: Class io.thorntail.example.NeedsGuava is on the classpath, but was ignored because a class it references was not found: com.google.common.cache.Cache from [Module \"deployment.guava-fail.war\" from Service Module Loader].
```
## What's Going on Here?
The `needs-guava` project contains a trivial CDI bean `io.thorntail.example.NeedsGuava` which has a dependency on the `com.google.common.cache.Cache` class provided by Guava. The project pom expresses a dependency on Guava version `27.0-jre`.

The `guava-fail` project contains a trivial JAX-RS application `io.thorntail.example.GuavaFail` which is also a CDI bean and injects `io.thorntail.example.NeedsGuava` from the `needs-guava` project. The `guava-fail` pom expresses a dependency on `needs-guava`.

This setup works fine until we activate the `with-dse` profile which expresses a dependency on the Datastax DSE driver version `1.7.0` whcih in turn depends on Guava version `19.0`. With the new dependencies, the thorntail plugin decides to strip the Guava JAR (and its dependencies) from the `WEB-INF/lib` directory in the WAR.
