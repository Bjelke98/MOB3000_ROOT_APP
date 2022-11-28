package com.example.mob3000_root_app.components.viewmodel

import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.navigation.Screen

class AppViewModel(
    var navController: NavHostController,
    var loginVM: LoginViewModel = LoginViewModel(),
    var articleVM: ArticleViewModel = ArticleViewModel(),
    var eventVM: EventViewModel = EventViewModel(),
    var ppArticleVM: PostPutArticleVM = PostPutArticleVM(),
    var ppEventVM: PostPutEventVM = PostPutEventVM(),
    val startDestination: String = Screen.Home.route
) : ViewModel() {

    fun<T> reverseArray(arr: List<T>): List<T?>{
        if(arr.size>1){
            val array = arr.toMutableList()
            var low = 0
            var high = array.size - 1
            while (low < high)
            {
                val temp = array[low]
                array[low] = array[high]
                array[high] = temp
                low++
                high--
            }
            return array
        }
        return arr
    }
}

// Kilde: https://stackoverflow.com/questions/67768746/chaining-modifier-based-on-certain-conditions-in-android-compose
// Metode som legger til et alternativ for if/else i modifier (Design)
fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: (Modifier.() -> Modifier)? = null
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else if (ifFalse != null) {
        then(ifFalse(Modifier))
    } else {
        this
    }
}