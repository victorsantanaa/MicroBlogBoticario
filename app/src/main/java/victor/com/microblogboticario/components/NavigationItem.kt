package victor.com.microblogboticario.components

import victor.com.microblogboticario.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home_bottom_navigation, "Home")
    object NewPost : NavigationItem("new_post", R.drawable.ic_new_post_bottom_navigation, "Novo")
    object News : NavigationItem("news", R.drawable.ic_provisorio_bottom_navigation, "Notícias Boticário")
}
