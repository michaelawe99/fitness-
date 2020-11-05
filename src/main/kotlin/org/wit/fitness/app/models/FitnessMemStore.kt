package org.wit.fitness.app.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class FitnessMemStore : FitnessStore {

    val calories = ArrayList<FitnessModel>()

    override fun findAll(): List<FitnessModel> {
        return calories
    }

    override fun findOne(id: Long) : FitnessModel? {
        var foundFitness: FitnessModel? = calories.find { p -> p.id == id }
        return foundFitness
    }

    override fun create(fitness: FitnessModel) {
        fitness.id = getId()
        calories.add(fitness)
        logAll()
    }

    override fun update(fitness: FitnessModel) {
        var foundCalorie = findOne(fitness.id!!)
        if (foundCalorie != null) {
            foundCalorie.meal = fitness.meal
            foundCalorie.calorie = fitness.calorie
        }
    }

    override fun delete(fitness: FitnessModel) {
        calories.remove(fitness)
    }

    internal fun logAll() {
        calories.forEach { logger.info("${it}") }
    }
}

