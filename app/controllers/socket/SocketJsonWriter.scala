package controllers.socket

import play.api.libs.json.{JsValue, Json, Writes}

/**
  * LoamStream
  * Created by oliverr on 5/9/2016.
  */
object SocketJsonWriter extends Writes[MessageToClient] {
  override def writes(message: MessageToClient): JsValue = message match {
    case GetDataResponse(dataSource, requester, data) =>
      Json.obj("dataSource" -> dataSource,
        "requester" -> requester,
        "data" -> data
      )
    case ErrorToClient(errorMessage) =>
      Json.obj("error" -> errorMessage)
    case _ =>
      Json.obj("error" -> s"Don't know what to do with $message.")
  }
}
