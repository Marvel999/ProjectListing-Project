package com.example.app.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.app.base.AppContents
import com.example.app.base.UiState
import com.example.app.entity.ProductListResItem
import com.example.app.ui.theme.AndroidStarterTheme
import com.example.app.viewmodel.ProductListingViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : ComponentActivity() {

    companion object{
        const val TAG = "TAG"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStarterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProductListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    @Composable
    fun ProductListScreen(modifier: Modifier = Modifier) {

        val viewmodel = hiltViewModel<ProductListingViewmodel>()
        val productRes = viewmodel.productList.collectAsState()
        LaunchedEffect(Unit) {
            viewmodel.fetchProductList()
        }
        Box(modifier = Modifier.fillMaxSize()) {
            when (productRes.value) {
                is UiState.Error -> {
                    Log.e(TAG, "Error")
                    Text(
                        text = (productRes.value as UiState.Error).msg, modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }

                is UiState.Loading -> {
                    Log.e(TAG, "Loading")
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }

                is UiState.Success<List<ProductListResItem>> -> {
                    Log.e(TAG, "Success")

                    val productList =
                        (productRes.value as UiState.Success<List<ProductListResItem>>)
                    LazyColumn {
                        items(
                            productList.data.size,
                            key = { it -> productList.data[it].id ?: it }) { index ->
                            ProductItem(productList.data.get(index)) { id ->
//                                if (id != null) {
//                                    val intent = Intent(this@ProductListActivity, ProductDetailsActivity::class.java)\
//                                    intent.putExtra(AppContents.PRODUCT_ID, )
//                                }
                            }
                        }
                    }
                }
            }
        }

    }


    @Composable
    fun ProductItem(productListResItem: ProductListResItem, onClick: (id: Int?) -> Unit) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp)
                .clickable { onClick.invoke(productListResItem.id) }) {
            AsyncImage(
                model = productListResItem.image,
                contentDescription = productListResItem.title ?: "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.8f)
            )
            Text(text = productListResItem.title?:"", fontSize = 18.sp) }
//            productListResItem.price?.let { Text(text = it, fontSize = 12.sp) }
//            productListResItem.description?.let { Text(text = it, fontSize = 12.sp) }
//            productListResItem.category?.let { Text(text = it, fontSize = 12.sp) }
        }
}