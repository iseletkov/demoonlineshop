package ru.studyit.demoonlineshop.viewmodel

import ru.studyit.demoonlineshop.modelfx.CGoodFX
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
    fun addNew()
    {
        goods.add(CGoodFX())
    }
    fun delete(
        good                                : CGoodFX?
    )
    {
//        if (good==null)
//            return
        good?:return
        serviceGoods.delete(good)

        
    }

}