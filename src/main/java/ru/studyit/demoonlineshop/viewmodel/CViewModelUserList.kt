package ru.studyit.demoonlineshop.viewmodel

import ru.studyit.demoonlineshop.controllers.CServiceUsers
import ru.studyit.demoonlineshop.modelfx.CUserFX
import tornadofx.ViewModel

class CViewModelUserList                    : ViewModel()
{
    val serviceUsers                        : CServiceUsers
                                            by inject()

    val users                               = serviceUsers.getAll()

    fun save()
    {
        serviceUsers.save(users)
    }
    fun save(
        user                                : CUserFX
    )
    {
        serviceUsers.save(user)
    }
}