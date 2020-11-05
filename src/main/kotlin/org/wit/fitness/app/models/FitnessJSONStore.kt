package org.wit.fitness.app.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.wit.fitness.app.helpers.exists
import org.wit.fitness.app.helpers.read
import org.wit.fitness.app.helpers.write

import org.wit.fitness.app.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "calories.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<FitnessModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class FitnessJSONStore : FitnessStore {

    var calories = mutableListOf<FitnessModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<FitnessModel> {
        return calories
    }

    override fun findOne(id: Long) : FitnessModel? {
        var foundFitness: FitnessModel? = calories.find { p -> p.id == id }
        return foundFitness
    }

    override fun create(fitness: FitnessModel) {
        fitness.id = generateRandomId()
        calories.add(fitness)
        serialize()
    }

    override fun update(fitness: FitnessModel) {
        var foundCalorie = findOne(fitness.id!!)
        if (foundCalorie != null) {
            foundCalorie.meal = fitness.meal
            foundCalorie.calorie = fitness.calorie
        }
        serialize()
    }

    override fun delete(fitness: FitnessModel) {
        calories.remove(fitness)
        serialize()
    }

    internal fun logAll() {
        calories.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(calories, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        calories = Gson().fromJson(jsonString, listType)
    }
}