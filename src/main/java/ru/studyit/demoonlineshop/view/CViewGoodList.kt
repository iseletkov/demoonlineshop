package ru.studyit.demoonlineshop.view

import ru.studyit.demoonlineshop.modelfx.CGoodFX
import ru.studyit.demoonlineshop.viewmodel.CViewModelGoodList
import tornadofx.*

class CViewGoodList                         : View("Товары") {

    val viewModelGoodList                   : CViewModelGoodList
                                            by inject()

    val table                               = tableview(viewModelGoodList.goods)  {
        readonlyColumn("ID", CGoodFX::id)
        column("Наименование", CGoodFX::propertyName).makeEditable()
    }

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
                    item("Добавить").action{
                        viewModelGoodList.addNew()
                    }
                    item("Удалить").action{
                        viewModelGoodList.delete(table.selectedItem)
                    }
                }
            }
        }
        center{
            this += table
        }
    }
}
