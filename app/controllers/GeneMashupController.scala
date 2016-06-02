package controllers

import play.api.mvc.{Action, Controller}

/**
  * LoamStream - Language for Omics Analysis Management
  * Created by oruebenacker on 6/2/16.
  */
class GeneMashupController extends Controller {

  def index = Action {
    Ok(views.html.Application.index())
  }

}
