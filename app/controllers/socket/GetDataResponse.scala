package controllers.socket

/**
  * LoamStream - Language for Omics Analysis Management
  * Created by oruebenacker on 6/2/16.
  */
case class GetDataResponse(dataSource: String, requester: String, data: String) extends MessageToClient{

}
