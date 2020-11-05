package org.wit.fitness.app.views

import org.wit.fitness.app.models.FitnessJSONStore
import org.wit.fitness.app.models.FitnessModel

class CaloriesView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Calorie")
        println(" 2. Update Calorie")
        println(" 3. List All Calories")
        println(" 4. Search Calories")
        println(" 5. Delete Calorie")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listCalories(calories : FitnessJSONStore) {
        println("List All Calories")
        println()
        calories.logAll()
        println()
    }

    fun showCalorie(fitness : FitnessModel) {
        if(fitness != null)
            println("Calorie Details [ $fitness ]")
        else
            println("Calorie Not Found...")
    }

    fun addCalorieData(fitness : FitnessModel) : Boolean {

        println()
        print("Enter a Meal : ")
        fitness.meal = readLine()!!
        print("Enter a Calorie : ")
        fitness.calorie = readLine()!!

        return fitness.meal.isNotEmpty() && fitness.calorie.isNotEmpty()
    }

    fun updateCalorieData(fitness : FitnessModel) : Boolean {

        var tempMeal: String?
        var tempCalorie: String?

        if (fitness != null) {
            print("Enter a new Meal for [ " + fitness.meal + " ] : ")
            tempMeal = readLine()!!
            print("Enter a new Calorie for [ " + fitness.calorie + " ] : ")
            tempCalorie = readLine()!!

            if (!tempMeal.isNullOrEmpty() && !tempCalorie.isNullOrEmpty()) {
                fitness.meal = tempMeal
                fitness.calorie = tempCalorie
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9

        return searchId
    }
}
