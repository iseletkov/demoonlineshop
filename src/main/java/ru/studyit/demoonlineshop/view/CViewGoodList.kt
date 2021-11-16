package ru.studyit.demoonlineshop.view

import ru.studyit.demoonlineshop.modelfx.CGoodFX
import ru.studyit.demoonlineshop.viewmodel.CViewModelGoodList
import tornadofx.*

class CViewGoodList                         : View("Товары") {

    val viewModelGoodList                   : CViewModelGoodList
                                            by inject()

    override val root                       = borderpane {
        top{
            menubar{
                menu("Данные") {
                    item("Пользователи").action {
                        replaceWith<CViewUserList>()
                    }
                }
                menu("Правка"){
                    item("Сохранить").action{
                        viewModelGoodList.save()
                    }
                }
            }
        }
        center{
            tableview(viewModelGoodList.goods)  {
                readonlyColumn("ID", CGoodFX::id)
                column("Наименование", CGoodFX::propertyName).makeEditable()
            }
        }
    }
}
