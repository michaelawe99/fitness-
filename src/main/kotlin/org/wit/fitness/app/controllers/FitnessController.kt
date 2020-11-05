package org.wit.fitness.app.controllers

import mu.KotlinLogging
import org.wit.fitness.app.models.FitnessJSONStore
import org.wit.fitness.app.models.FitnessModel
import org.wit.fitness.app.views.CaloriesView

class FitnessController {

    val calories = FitnessJSONStore()
    val calorieView = CaloriesView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Calorie Console App" }
        println("Calorie Kotlin App Version 4.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Calorie Console App" }
    }

    fun menu() :Int { return calorieView.menu() }

    fun add(){
        var aCalorie = FitnessModel()

        if (calorieView.addCalorieData(aCalorie))
            calories.create(aCalorie)
        else
            logger.info("Calorie Not Added")
    }

    fun list() {
        calorieView.listCalories(calories)
    }

    fun update() {

        calorieView.listCalories(calories)
        var searchId = calorieView.getId()
        val aCalorie = search(searchId)

        if(aCalorie != null) {
            if(calorieView.updateCalorieData(aCalorie)) {
                calories.update(aCalorie)
                calorieView.showCalorie(aCalorie)
                logger.info("Calorie Updated : [ $aCalorie ]")
            }
            else
                logger.info("Calorie Not Updated")
        }
        else
            println("Calorie Not Updated...")
    }

    fun delete() {
        calorieView.listCalories(calories)
        var searchId = calorieView.getId()
        val aCalorie = search(searchId)

        if(aCalorie != null) {
            calories.delete(aCalorie)
            println("Calorie Deleted...")
            calorieView.listCalories(calories)
        }
        else
            println("Calorie Not Deleted...")
    }

    fun search() {
        val aCalorie = search(calorieView.getId())!!
        calorieView.showCalorie(aCalorie)
    }


    fun search(id: Long) : FitnessModel? {
        var foundCalorie = calories.findOne(id)
        return foundCalorie
    }

    fun dummyData() {
        calories.create(FitnessModel(meal = "Burrito", calorie = "326 Cal"))
        calories.create(FitnessModel(meal= "Chicken Marsala", calorie = "2209 cal"))
        calories.create(FitnessModel(meal = "chicken Caesar Salad", calorie = "392 cal"))
    }
}

