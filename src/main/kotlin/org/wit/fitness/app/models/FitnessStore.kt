package org.wit.fitness.app.models

interface FitnessStore {
    fun findAll(): List<FitnessModel>
    fun findOne(id: Long): FitnessModel?
    fun create(fitness: FitnessModel)
    fun update(fitness: FitnessModel)
    fun delete(fitness: FitnessModel)
}