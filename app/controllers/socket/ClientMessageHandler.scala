package controllers.socket

import controllers.socket.ClientMessageHandler.OutMessageSink
import data.DataProviders

import scala.concurrent.ExecutionContext

/**
  * LoamStream
  * Created by oliverr on 5/9/2016.
  */
object ClientMessageHandler {

  object OutMessageSink {

    object NoOp extends OutMessageSink {
      override def send(outMessage: MessageToClient): Unit = ()
    }

  }

  trait OutMessageSink {
    def send(outMessage: MessageToClient)
  }

}

case class ClientMessageHandler(outMessageSink: OutMessageSink)(implicit executionContext: ExecutionContext) {

  def handleInMessage(inMessage: MessageFromClient): Unit = {
    inMessage match {
      case GetDataRequest(dataSources, requester, geneName) =>
        dataSources.foreach({ dataSource =>
          val data = DataProviders.getData(geneName, dataSource)
          outMessageSink.send(GetDataResponse(dataSource, requester, data))
        })
      case _ =>
        outMessageSink.send(ErrorToClient(s"Don't know what to do with incoming socket message '$inMessage'."))
    }
  }
}
