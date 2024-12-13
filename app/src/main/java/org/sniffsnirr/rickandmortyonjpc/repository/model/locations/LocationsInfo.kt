package org.sniffsnirr.rickandmortyonjpc.repository.model.locations

data class LocationsInfo(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)