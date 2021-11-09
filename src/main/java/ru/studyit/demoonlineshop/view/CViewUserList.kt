package ru.studyit.demoonlineshop.view

import javafx.scene.layout.BorderPane
import ru.studyit.demoonlineshop.controllers.CControllerUsers
import ru.studyit.demoonlineshop.model.CUser
import tornadofx.*
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField


class CViewUserList : View() {
    val controller: CControllerUsers by inject()

    override val root = tableview(controller.users)  {
        readonlyColumn("ID", CUser::id)
        readonlyColumn("Логин",CUser::login)
        readonlyColumn("Дата рождения", CUser::dateOfBirth)
        readonlyColumn("Возраст",CUser::age)
    }

}