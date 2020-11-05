package org.wit.fitness.app.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.fitness.app.controllers.FitnessUIController
import tornadofx.*

class MenuScreen : View("Calories In Calories Out ") {

    val fitnessUIController: FitnessUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Todays Calories") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        fitnessUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Calories") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        fitnessUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}