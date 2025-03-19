package com.space.travel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import kotlin.system.exitProcess


fun main() {

    println("Welcome to the space, Earthling! \uD83D\uDE80\uD83C\uDF0C")
    runBlocking {
        selectFunction()
    }}

fun selectFunction(){
    println("1) Calculate space travel.")
    println("2) Planet information.")
    println("3) Exit")
    print("PLease select: ")
    val choice = readln()
    when (choice) {
        "1" -> spaceTravel()
        "2" -> runBlocking {
        getPlanets()
        }
        "3" -> exitProcess(0)
        else -> typoByUser()
    }
}

fun typoByUser(){
    println("Wrong text!")
    selectFunction()
}

fun showBasicInfo(respond: Response<PlanetData>) {

    println("English name: ${respond.body()?.englishName}")
    println("Original name: ${respond.body()?.name}")
    println("Type: ${respond.body()?.bodyType}")



}

fun showDetailedInfo(respond: Response<PlanetData>) {
    println("Gravity: ${respond.body()?.gravity}")
    println("Average temperature: ${respond.body()?.avgTemp}")
    println("Discovered by: ${respond.body()?.discoveredBy}")
    println("Type: ${respond.body()?.bodyType}")
    println("Density: ${respond.body()?.density}")
    println("Mean Radius: ${respond.body()?.meanRadius}")
    print("Moons: ")
    respond?.body()?.moons?.forEach{
        print("$it ,")
    }
}
suspend fun getPlanets(){
    println("Write the name of the planet for information, or back for get to the main menu.")
    val planetNameInput = readln().lowercase()

        val respond = SolaireClient.instance.getPlanetInfo(planetNameInput)
        if(respond.isSuccessful){

            println("1) Basic info.")
            println("2) Detailed info.")
            println("Select an option: ")
            val option = readln()
            when(option){
                "1"->showBasicInfo(respond)
                "2"->{
                    showBasicInfo(respond)
                    showDetailedInfo(respond)
                }
                else->typoByUser()
            }
            selectFunction()
        }else{
            println("Error: ${respond.errorBody()?.toString()}")
            selectFunction()
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
