package ru.studyit.demoonlineshop.modelfx

import tornadofx.getProperty
import tornadofx.property
import java.time.LocalDate
import java.time.Period
import java.util.*

class CUserFX(
    val id                                  : UUID?
                                            = null,
    login                                   : String
                                            = "",
    dayOfBirth                              : LocalDate?
                                            = null,
    sex                                     : Boolean
                                            = true,
    val orderCount                          : Int
                                            = 0
)
{
    var login                               by property(login)
    fun propertyLogin()                     = getProperty(CUserFX::login)

    var sex                                 by property(sex)
    fun propertySex()                       = getProperty(CUserFX::sex)

    var dateOfBirth                         by property(dayOfBirth)
    fun propertyDateOfBirth()               = getProperty(CUserFX::dateOfBirth)

    val age                                 : Int
        get()                               {
            return if (dateOfBirth==null)
                0
            else
                Period.between(dateOfBirth, LocalDate.now()).years
        }
}