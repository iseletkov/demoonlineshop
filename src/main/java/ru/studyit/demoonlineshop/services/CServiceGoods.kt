package ru.studyit.demoonlineshop.services

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import ru.studyit.demoonlineshop.config.CConfigHibernate
import ru.studyit.demoonlineshop.dao.CDAOGoods
import ru.studyit.demoonlineshop.model.CGood
import ru.studyit.demoonlineshop.modelfx.CGoodFX
import ru.studyit.demoonlineshop.modelfx.CUserFX
import tornadofx.Controller

class CServiceGoods                         : Controller()
{
    private var daoGoods                    = CDAOGoods(CConfigHibernate.getSessionFactory())

    val goods                               = FXCollections.observableArrayList<CGoodFX>()

    fun getAll()                            : ObservableList<CGoodFX>
    {
        goods.clear()

//        var goodFx                          : CGoodFX
//        val goodsFromDB = daoGoods.all
//
//        val tempList                        = ArrayList<CGoodFX>()
//        for (good in goodsFromDB)
//        {
//            goodFx = CGoodFX(good.id, good.name)
//            tempList.add(goodFx)
//        }
//        goods.addAll(tempList)


        goods
            .addAll(
                daoGoods.all
                    .map { good->
                        CGoodFX(good.id, good.name)
                    }
            )
        return goods
    }
    fun save(
        goods                               : List<CGoodFX>
    )
    {
//        for (goodfx in goods)
//        {
//            val good = CGood(goodfx.id, goodfx.name)
//            if (good.id==null)
//                daoGoods.save(good)
//            else
//                daoGoods.update(good)
//        }
        goods
            .asSequence()
            .map{   goodfx ->
                CGood(goodfx.id, goodfx.name)
            }




    }


}