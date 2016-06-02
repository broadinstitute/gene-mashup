package controllers.socket

/**
  * LoamStream - Language for Omics Analysis Management
  * Created by oruebenacker on 6/2/16.
  */
case class GetDataRequest(dataSources: Seq[String], requester: String, geneName: String)
  extends MessageFromClient {

}
