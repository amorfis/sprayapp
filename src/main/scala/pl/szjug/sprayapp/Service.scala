package pl.szjug.sprayapp

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

class Service extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}

// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            val jVersion = System.getProperty("java.version")

            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
                <p>We are running Java {jVersion}</p>
              </body>
            </html>
          }
        }
      }
    }
}
