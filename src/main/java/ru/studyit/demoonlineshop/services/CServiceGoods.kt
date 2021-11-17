package ru.studyit.demoonlineshop.services

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import ru.studyit.demoonlineshop.config.CConfigHibernate
import ru.studyit.demoonlineshop.dao.CDAOGoods
import ru.studyit.demoonlineshop.dao.CDAOOrders
import ru.studyit.demoonlineshop.model.CGood
import ru.studyit.demoonlineshop.modelfx.CGoodFX
import ru.studyit.demoonlineshop.modelfx.CUserFX
import tornadofx.Controller
import java.util.Comparator

class CServiceGoods                         : Controller()
{
    private val daoGoods                    = CDAOGoods(CConfigHibernate.getSessionFactory())
    private val daoOrders                   = CDAOOrders(CConfigHibernate.getSessionFactory())
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

        goods.sortWith(Comparator.comparing(CGoodFX::name))
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
        val goodsSeq                        = goods
            .asSequence()
            .map{   goodfx ->
                CGood(goodfx.id, goodfx.name)
            }

        val goodsToSave                     = goodsSeq
            .filter{ good ->
                good.id==null
            }
            .toList()

        daoGoods.saveList(goodsToSave)

        val goodsToUpdate                   = goodsSeq
            .filterNot{ good ->
                good.id==null
            }
            .toList()

        daoGoods.updateList(goodsToUpdate)

        getAll()
    }

    fun delete(
        goodfx                              : CGoodFX
    )
    {
        val good                            = daoGoods.get(goodfx.id)
        if (good == null)
        {
            goods.remove(goodfx)
            return
        }
        val orders                          = daoOrders.getByGood(good)
        if (orders.size==0)
        {
            daoGoods.delete(CGood(goodfx.id, goodfx.name))
            goods.remove(goodfx)
            return
        }
        //TODO Уведомить пользователя о том, что у товара есть заказы,
        //и их надо удалить до удаления товара.
        return
    }


}