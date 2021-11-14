package ru.studyit.demoonlineshop

import javafx.scene.Scene
import ru.studyit.demoonlineshop.view.CViewUserList
import tornadofx.App
import tornadofx.UIComponent

class CApplication : App(CViewUserList::class)
{
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 800.0, 600.0)
}
