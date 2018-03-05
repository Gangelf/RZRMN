package com.rzrmn.Model

/**
 * Created by Gangelf on 3/5/2018.
 */
class Location{

    var trailName: String = ""
    var Difficulty: String = ""
    var vehiclesAllowed: String = ""
    var mapURL: String = ""
    var trailLength: String = ""
    var whenOpen: String = ""
    var Lat: Double = 0.0
    var Long: Double = 0.0

    constructor(){

    }

    constructor(trailName: String, Difficulty: String, vehiclesAllowed: String, mapURL: String, trailLength: String, whenOpen: String, Lat: Double, Long: Double){
        this.trailName = trailName
        this.Difficulty = Difficulty
        this.vehiclesAllowed = vehiclesAllowed
        this.mapURL = mapURL
        this.trailLength = trailLength
        this.whenOpen = whenOpen
        this.Lat = Lat
        this.Long = Long
    }
}