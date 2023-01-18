package com.udacity.shoestore.domain

import com.udacity.shoestore.models.Shoe


class GenerateListOfShoesUseCase {
    operator fun invoke(): MutableList<Shoe> = mutableListOf(
        Shoe("Ultra Boost Web DNA", 45.0, "adidas", "Running sneakers"),
        Shoe("4D FWD Pulse", 40.0, "adidas", "Running sneakers"),
        Shoe("Gel-Cumulus 24", 42.5, "Asics", "Running sneakers"),
        Shoe("x A-Cold-Wall* Aeon Active CX", 40.5, "Converse", "Running sneakers"),
        Shoe("237", 40.0, "New Balance", "Running sneakers"),
        Shoe("Fresh Foam X 880v12", 39.0, "New Balance", "Running sneakers"),
        Shoe("Toundra Pro CSWP", 44.5, "Salomon", "Winter boots"),
        Shoe("Abisko GTX", 45.5, "Hanwag", "Winter boots"),
        Shoe("Nordkap Pro GTX", 42.0, "Meindl", "Winter boots"),
        Shoe("PAY PLS", 37.5, "BABE", "Women sandals")
    )
}