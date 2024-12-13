package org.sniffsnirr.rickandmortyonjpc.repository.model.locations

data class Locations(
    val info: Info,
    val results: List<LocationsInfo>
)