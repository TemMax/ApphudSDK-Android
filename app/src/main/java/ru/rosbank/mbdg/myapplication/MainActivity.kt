package ru.rosbank.mbdg.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.SkuDetails
import com.apphud.sdk.ApphudListener
import com.apphud.sdk.ApphudSdk
import ru.rosbank.mbdg.myapplication.presentation.ProductModelMapper
import ru.rosbank.mbdg.myapplication.presentation.ProductsAdapter

class MainActivity : AppCompatActivity() {

    private val mapper = ProductModelMapper()
    private val adapter = ProductsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listener = object : ApphudListener {
            override fun apphudFetchSkuDetailsProducts(details: List<SkuDetails>) {
                val products =  details.map { mapper.map(it) }
                adapter.products = adapter.products.filter { it.details != null } + products
            }
        }
        ApphudSdk.setListener(listener)

        adapter.onClick = { model ->
            Log.e("Apphud", "onClick model: $model")
            when (model.details) {
                null -> Log.e("Apphud", "details is empty")
                else ->  ApphudSdk.purchase(this, model.details) { _ -> }
            }
        }

        val syncButton: Button = findViewById(R.id.syncButtonViewId)
        syncButton.setOnClickListener {
            ApphudSdk.syncPurchases()
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewId)
        recyclerView.adapter = adapter
    }
}