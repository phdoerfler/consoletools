# Console Tools

Who doesn't love ASCII spinners?

## Usage

### build.sbt

```scala
resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += "io.doerfler" % "consoletools" % "main-SNAPSHOT"
```

### Code

```scala
import io.doerfler.console._

object Main extends App with SpinnerSupport {
  Spinner show "Searching…" whileWaitingFor seachResultF
  val searchResult = Await.result(seachResultF, 60.seconds)

  def seachResultF: Future[String] = {
    Thread.sleep(1000)
    "Mich dünkt, die Alte spricht im Fieber."
  }
}
```