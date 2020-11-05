package org.wit.fitness.app.views


import org.wit.fitness.app.controllers.FitnessUIController
import org.wit.fitness.app.models.FitnessModel
import tornadofx.*

class ListCaloriesScreen : View("List Calories") {

    val fitnessUIController: FitnessUIController by inject()
    val tableContent = fitnessUIController.calorie.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", FitnessModel::id)
            readonlyColumn("MEAL", FitnessModel::meal)
            readonlyColumn("CALORIE", FitnessModel::calorie)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    fitnessUIController.closeList()
                }
            }
        }
    }

}
