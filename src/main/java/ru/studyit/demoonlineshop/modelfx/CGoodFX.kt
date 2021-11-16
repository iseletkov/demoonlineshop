package ru.studyit.demoonlineshop.modelfx

import tornadofx.getProperty
import tornadofx.property
import java.util.*

class CGoodFX(
    val id                                  : UUID?
                                            = null,
    name                                    : String
                                            = ""
)
{
    var name                               by property(name)
    fun propertyName()                     = getProperty(CGoodFX::name)
}