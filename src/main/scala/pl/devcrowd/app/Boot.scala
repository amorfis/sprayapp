package pl.devcrowd.app

import spray.can.Http
import akka.io.IO
import akka.actor.{ActorSystem, Props}
import spray.routing.{SimpleRoutingApp}

object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")

  // create and start our service actor
  val service = system.actorOf(Props[Service], "demo-service")

  val port = System.getProperty("app.port")

  println(s"Starting spray can on $port")

  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ! Http.Bind(
    service,
    interface = "0.0.0.0",
    port = port.toInt)

}
