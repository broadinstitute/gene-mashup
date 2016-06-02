package controllers.socket

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * LoamStream
  * Created by oliverr on 5/9/2016.
  */
object SocketJsonReader extends Reads[MessageFromClient]{
  override def reads(json: JsValue): JsResult[MessageFromClient] = {
    ((json \"dataSources").validate[Seq[String]] and (json \"requester").validate[String]
    and (json \ "geneName").validate[String])(GetDataRequest)
  }
}
