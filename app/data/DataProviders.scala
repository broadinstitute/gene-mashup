package data

/**
  * LoamStream - Language for Omics Analysis Management
  * Created by oruebenacker on 6/2/16.
  */
object DataProviders {

  def getData(geneName: String, dataSource: String): String = {
    s"Source $dataSource has awesome things to say about $geneName."
  }

}
