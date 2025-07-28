package com.study.conversordemoeda

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.study.conversordemoeda.UI.CurrencyTypesAdapter
import com.study.conversordemoeda.databinding.ActivityMainBinding
import com.study.conversordemoeda.network.model.CurrencyType
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val viewModel by viewModels<CurrencyExchangeViewModel>()


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    viewModel.requireCurrencyTypes()

    lifecycleScope.apply {
      launch {
        viewModel.currencyTypes.collect { result ->
          result.onSuccess { currencyTypes ->
            binding.configureCurrencyTypesSpinners(currencyTypes = currencyTypes)
          }.onFailure {
            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
          }
        }
      }
      launch {
        viewModel.currencyTypes.collect { result ->
          result.onSuccess {
            Log.d("MainActivity", it.toString())
          }.onFailure {
            Log.d("MainActivity", it.message.toString())
          }
        }
      }
    }
  }

  private fun ActivityMainBinding.configureCurrencyTypesSpinners(currencyTypes: List<CurrencyType>) {
    spinnerFromExchange.apply {
      adapter = CurrencyTypesAdapter(currencyTypes)
      onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
          parent: AdapterView<*>?,
          view: View?,
          position: Int,
          id: Long
        ) {

          // Requisita a taxa de câmbio quando uma moeda é selecionada
          val from = currencyTypes[position]
          val to = currencyTypes[spinnerToExchange.selectedItemPosition]

          viewModel.requireExchangeRate(
            from = from.acronym,
            to = to.acronym
          )
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
      }
    }
    spinnerToExchange.apply {
      adapter = CurrencyTypesAdapter(currencyTypes)
      onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
          parent: AdapterView<*>?,
          view: View?,
          position: Int,
          id: Long
        ) {

          // Requisita a taxa de câmbio quando uma moeda é selecionada
          val from = currencyTypes[position]
          val to = currencyTypes[spinnerToExchange.selectedItemPosition]

          viewModel.requireExchangeRate(
            from = from.acronym,
            to = to.acronym
          )
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
          currencyTypes.firstOrNull()?.let { firstCurrencyType ->
            viewModel.requireExchangeRate(
              from = firstCurrencyType.acronym,
              to = firstCurrencyType.acronym
            )
          }
        }
      }
    }
  }
}
