package org.wit.fitness.app.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.fitness.app.controllers.FitnessUIController
import tornadofx.*

class AddCaloriesScreen : View("Add Calorie") {
    val model = ViewModel()
    val _meal = model.bind { SimpleStringProperty() }
    val _calories = model.bind { SimpleStringProperty() }
    val fitnessUIController: FitnessUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Meal") {
                textfield(_meal).required()
            }
            field("Calories") {
                textarea(_calories).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        fitnessUIController.add(_meal.value,_calories.value)

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        fitnessUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _meal.value = ""
        _calories.value = ""
        model.clearDecorators()
    }
}

