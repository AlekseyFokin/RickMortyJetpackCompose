package org.sniffsnirr.rickandmortyonjpc.repository.model.locations

data class Location(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val type: String,
    val url: String
)