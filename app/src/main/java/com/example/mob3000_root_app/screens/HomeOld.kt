package com.example.mob3000_root_app.screens

//
//@Composable
//fun Home(navController: NavHostController) {
//
//    Box( Modifier.background(MaterialTheme.colorScheme.surfaceVariant) )
//    {
//        val articleList = ArticleTestdata().dataList
//
//        Column(Modifier.padding(10.dp),
//            verticalArrangement = Arrangement.spacedBy(15.dp))
//        {
//
//            Text(   text = "Recent Articles",
//                Modifier.fillMaxWidth(),
//                style =  MaterialTheme.typography.headlineLarge,
//                textAlign = TextAlign.Center
//            )
//
//            LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp))
//            {
//                items(items = articleList){ article ->
//                    Article(navController, articleData = article, ArticleType.VERTICAL_ARTICLE, )
//                }
//            }
//        }
//    }
//}