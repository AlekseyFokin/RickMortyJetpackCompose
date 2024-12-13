package org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.model

import org.sniffsnirr.rickandmortyonjpc.repository.model.characters.Location

data class Character(
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val episode: List<Int>,
)