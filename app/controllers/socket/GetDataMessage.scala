package controllers.socket

/**
  * LoamStream - Language for Omics Analysis Management
  * Created by oruebenacker on 6/2/16.
  */
case class GetDataMessage(geneName: String, requester: String, dataSources: Seq[String])
  extends MessageFromClient {

}
