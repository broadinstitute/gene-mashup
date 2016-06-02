package controllers.socket

import play.api.libs.json.{JsValue, Json, Writes}

/**
  * LoamStream
  * Created by oliverr on 5/9/2016.
  */
object SocketJsonWriter extends Writes[MessageToClient] {
  override def writes(message: MessageToClient): JsValue = Json.obj(
    ???
  )
}
