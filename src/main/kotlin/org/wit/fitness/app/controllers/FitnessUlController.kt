package org.wit.fitness.app.controllers

import mu.KotlinLogging
import org.wit.fitness.app.models.FitnessJSONStore
import org.wit.fitness.app.models.FitnessModel
import org.wit.fitness.app.views.AddCaloriesScreen
import org.wit.fitness.app.views.ListCaloriesScreen
import org.wit.fitness.app.views.MenuScreen
import tornadofx.*

class FitnessUIController : Controller() {

    val calorie = FitnessJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Calorie TornadoFX UI App" }
    }
    fun add(_meal : String, _calorie : String){

        var aCalorie = FitnessModel(meal = _meal, calorie = _calorie)
        calorie.create(aCalorie)
        logger.info("Calorie Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListCaloriesScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        calorie.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddCaloriesScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddCaloriesScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListCaloriesScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}

