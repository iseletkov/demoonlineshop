package ru.studyit.demoonlineshop.controllers

import javafx.collections.FXCollections
import ru.studyit.demoonlineshop.config.CConfigHibernate
import ru.studyit.demoonlineshop.dao.CDAOUsers
import ru.studyit.demoonlineshop.model.CUser
import tornadofx.Controller
import tornadofx.asObservable

class CControllerUsers : Controller()
{
    var daoUsers = CDAOUsers(CConfigHibernate.getSessionFactory())
    val users = daoUsers.all.asObservable()
}