package com.space.travel

import kotlin.system.exitProcess

data class PlanetData(val name: String, val color: String, val gravitation: Double, val moonNumber: Int)

fun main() {
    println("Welcome to the space, Earthling! \uD83D\uDE80\uD83C\uDF0C")
    selectFunction()
}

fun selectFunction(){
    println("If you want to calculate space travel, write calculate, for planet information, write planet, to exit, write exit.")
    val choice = readln()
    when (choice) {
        "calculate" -> spaceTravel()
        "planet" -> planetData()
        "exit" -> exitProcess(0)
        else -> typoByUser()
    }
}

fun typoByUser(){
    println("Wrong text!")
    selectFunction()
}

fun planetData() {
    val planets = listOf(
        PlanetData("Earth", "Blue planet", 1.0, 1),
        PlanetData("Mars", "Red planet", 0.38, 2),
        PlanetData("Venus", "Golden brown planet", 0.91, 0)
    )
    println("Write the name of the planet for information, or back for get to the main menu.")
    val planetNameInput = readln()
    val planet = planets.find { it.name.equals(planetNameInput, ignoreCase = true) }
    if (planetNameInput == "back") { selectFunction() }
    if (planet != null) {
        println("${planet.name}: ${planet.color}, gravity: ${planet.gravitation} G,  moons: ${planet.moonNumber}")
        planetData()
    }
    else { println("I don't have information about this planet.")
        planetData()
    }
}

fun spaceTravel() {
    val travelDistance = getTravelDistance()
    val travelSpeed = getTravelSpeed()
    val travelTime = calculateTravelTime(travelDistance, travelSpeed)
    println("Thank you! The estimated time of your space travel is ${String.format("%.2f", travelTime)} days.")
    exitOrNot()
}

fun getTravelDistance(): Double {
    println("If you want to know the time of your space travel, " +
            "at first, please type in the distance in light years of the travel! ")
    val travelDistanceVar = readln()
    val travelDistance = travelDistanceVar.toDoubleOrNull()
    if (travelDistance == null) {
        println("Please type in only numbers")
        return getTravelDistance()
    }
    return travelDistance
}

fun getTravelSpeed(): Double {
    println("Please inform me about the speed in km/s you will go with through the universe!")
    val travelSpeedVar = readln()
    val travelSpeed = travelSpeedVar.toDoubleOrNull()
    if (travelSpeed == null) {
        println("Please type in only numbers")
        return getTravelSpeed()
    }
    return travelSpeed
}

fun calculateTravelTime(travelDistance: Double, travelSpeed: Double): Double {
    return (travelDistance * 9460730472580.8) / (travelSpeed * 60 * 60 * 24)
}

fun exitOrNot() {
    println("Do you want to calculate another space trip? If yes, type Y, if not, please type N!" +
            "If you want to go back to the main menu, type back.")
    val userAnswer = readln().uppercase()
    when (userAnswer) {
        "N" -> exitProcess(0)
        "Y" -> spaceTravel()
        "BACK" -> selectFunction()
        else -> {
            println("Please type in a valid character!")
            exitOrNot()
        }
    }
}
