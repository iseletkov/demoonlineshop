package ru.studyit.demoonlineshop.viewmodel

import ru.studyit.demoonlineshop.services.CServiceGoods
import tornadofx.ViewModel

class CViewModelGoodList                    : ViewModel() {
    val serviceGoods                        : CServiceGoods
                                            by inject()

    val goods                               = serviceGoods.getAll()

    fun save()
    {
        serviceGoods.save(goods)
    }


}