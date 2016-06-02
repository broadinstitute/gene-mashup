package controllers.socket

import akka.actor.{Actor, ActorRef, Props}
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
  * LoamStream
  * Created by oliverr on 5/9/2016.
  */
object WebSocketReceiveActor {
  def props(sendActor: ActorRef): Props = Props(new WebSocketReceiveActor(sendActor))
}

class WebSocketReceiveActor(sendActor: ActorRef) extends Actor with ClientMessageHandler.OutMessageSink {

  val clientHandler = ClientMessageHandler(this)

  override def receive: Receive = {
    case json: JsValue =>
      json.validate(SocketJsonReader) match {
        case JsSuccess(inMessage, _) =>
          clientHandler.handleInMessage(inMessage)
        case error: JsError =>
          val outJson = Json.obj("type" -> "error", "error" -> JsError.toJson(error))
          sendActor ! outJson
      }
  }

  override def send(outMessage: MessageToClient): Unit = {
    println("OUT")
    println(outMessage)
    sendActor ! SocketJsonWriter.writes(outMessage)
  }
}
