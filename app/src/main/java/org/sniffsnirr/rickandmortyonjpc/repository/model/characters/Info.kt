package org.sniffsnirr.rickandmortyonjpc.repository.model.characters

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
)