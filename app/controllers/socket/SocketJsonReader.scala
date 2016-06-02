package controllers.socket

import play.api.libs.json._

/**
  * LoamStream
  * Created by oliverr on 5/9/2016.
  */
object SocketJsonReader extends Reads[MessageFromClient]{
  override def reads(json: JsValue): JsResult[MessageFromClient] = ???
}
