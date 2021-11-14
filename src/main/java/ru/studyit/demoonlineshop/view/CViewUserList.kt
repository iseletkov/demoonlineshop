package ru.studyit.demoonlineshop.view

import javafx.scene.layout.BorderPane
import ru.studyit.demoonlineshop.modelfx.CUserFX
import ru.studyit.demoonlineshop.viewmodel.CViewModelEditUser
import ru.studyit.demoonlineshop.viewmodel.CViewModelUserList
import tornadofx.*

class CViewUserList : View("Пользователи в системе") {

    val viewModelUserList                   : CViewModelUserList
                                            by inject()
    val viewModelEditUser                   = CViewModelEditUser(CUserFX())

    override val root                       = BorderPane()

    val tableView                           = tableview(viewModelUserList.users)  {
        readonlyColumn("ID", CUserFX::id)
        column("Логин", CUserFX::propertyLogin).makeEditable()
        column("Пол", CUserFX::propertySex).makeEditable()
        column("Дата рождения", CUserFX::propertyDateOfBirth).makeEditable()
        readonlyColumn("Возраст", CUserFX::age)
        readonlyColumn("Количество заказов", CUserFX::orderCount)

        // Update the person inside the view model on selection change
        viewModelEditUser.rebindOnChange(this) { selectedPerson ->
            item                = selectedPerson ?: CUserFX()
        }
    }

    init {
        with(root) {
            top {
                menubar {
                    menu("Данные") {
                        item("Сохранить").action {
                            viewModelUserList.save()
                        }
                        item("Добавить").action {
                            tableView.selectionModel.clearSelection()
                        }
                    }
                }

            }
            center {
                this += tableView
            }
            right {
                form {
                    fieldset("Редактирование пользователя") {
                        field("Логин") {
                            textfield(viewModelEditUser.login)
                        }
                        field("Дата рождения") {
                            datepicker(viewModelEditUser.dateOfBirth)
                        }
                        field("Пол") {
                            choicebox(viewModelEditUser.sex)

                        }
                        button("Сохранить") {
                            enableWhen(viewModelEditUser.dirty)
                            action {
                                save()
                            }
                        }
                        button("Отмена").action {
                            viewModelEditUser.rollback()
                        }
                    }
                }
            }
        }
    }
    private fun save() {
        // Flush changes from the text fields into the model
        viewModelEditUser.commit()

        viewModelUserList.save(viewModelEditUser.item)
    }



}