package org.sniffsnirr.rickandmortyonjpc

sealed class NavigationMenuItem(val title:String,val icon:Int, val route:String) { //элементы навигационной панели
    object ListCharectersScreen:NavigationMenuItem("List Characters",R.drawable.ic_list_24, SCREEN_1)
    object CharecterScreen:NavigationMenuItem("Character",R.drawable.ic_charect_24, SCREEN_2)
    object ListLocationsScreen:NavigationMenuItem("List Locations",R.drawable.ic_location_24, SCREEN_3)

    companion object{
        const val SCREEN_1="SCREEN_1"
        const val SCREEN_2="SCREEN_2"
        const val SCREEN_3="SCREEN_3"
    }
}
