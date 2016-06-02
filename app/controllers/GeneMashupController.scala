package controllers

import akka.actor.ActorSystem
import akka.stream.Materializer
import com.google.inject.Inject
import controllers.socket.WebSocketReceiveActor
import play.api.libs.json.JsValue
import play.api.libs.streams.ActorFlow
import play.api.mvc.{Action, Controller, WebSocket}

/**
  * LoamStream - Language for Omics Analysis Management
  * Created by oruebenacker on 6/2/16.
  */
class GeneMashupController @Inject()(implicit system: ActorSystem, materializer: Materializer)
  extends Controller {

  def index = Action {
    Ok(views.html.Application.index())
  }

  def socket: WebSocket = WebSocket.accept[JsValue, JsValue] { request =>
    ActorFlow.actorRef(sendActor => WebSocketReceiveActor.props(sendActor))
  }

}
