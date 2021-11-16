package ru.studyit.demoonlineshop.services


import javafx.collections.FXCollections
import javafx.collections.ObservableList
import ru.studyit.demoonlineshop.config.CConfigHibernate
import ru.studyit.demoonlineshop.dao.CDAOUsers
import ru.studyit.demoonlineshop.modelfx.CUserFX
import ru.studyit.demoonlineshop.model.CUser
import tornadofx.Controller

class CServiceUsers                         : Controller()
{
    private var daoUsers                    = CDAOUsers(CConfigHibernate.getSessionFactory())

    val users                               = FXCollections.observableArrayList<CUserFX>()

    fun getAll()                            : ObservableList<CUserFX>
    {
        users.clear()
        users
            .addAll(
                daoUsers.all
                    .map {user->
                        CUserFX(user.id, user.login, user.dateOfBirth, user.sex, user.orders.size)
                    }
            )
        return users
    }
    fun save(
        user                                : CUserFX
    )
    {
        val user1                           = CUser(user.id, user.sex, user.login, user.dateOfBirth)
        if (user1.id==null)
            daoUsers.save(user1)
        else
            daoUsers.update(user1)
        getAll()
    }

    fun save(
        users                               : List<CUserFX>
    )
    {
        val seq                             = users.asSequence()
            .map { user ->
                CUser(user.id, user.sex, user.login, user.dateOfBirth)
            }

        val newUsers                        = seq
            .filter {
                it.id==null
            }
            .toList()

        daoUsers.saveList(newUsers)

        val existingUsers                   = seq
            .filterNot {
                it.id==null
            }
            .toList()

        daoUsers.updateList(existingUsers)
        getAll()
    }
}