package org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import org.sniffsnirr.rickandmortyonjpc.repository.Repository
import org.sniffsnirr.rickandmortyonjpc.repository.model.locations.Location
import org.sniffsnirr.rickandmortyonjpc.repository.model.locations.LocationsInfo

class LocationsPagingSource(val repository: Repository) : PagingSource<Int, Location>() {

    override fun getRefreshKey(state: PagingState<Int, Location>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getLocations(page)
        }.fold(
            onSuccess = {
                Page(
                    data = castListResultToListLocation(it),
                    prevKey = params.key?.let { it-1 },
                    nextKey = if (it?.isEmpty() == true) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }

    private fun castListResultToListLocation(listResult: List<LocationsInfo>?): List<Location> {
        val locationList = mutableListOf<Location>()
        listResult?.map { result ->
            val location = Location(
                result.created,
                result.dimension,
                result.id,
                result.name,
                result.type,
                result.url
            )
            locationList.add(location)
        }
        return locationList
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}