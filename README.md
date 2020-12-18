# Console Tools



```scala
import io.doerfler.console._

Spinner show "Searching…" whileWaitingFor seachResultF
val searchResult = Await.result(seachResultF, 60.seconds)

def seachResultF: Future[String] = ???
```