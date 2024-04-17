package com.crisjrrbndhn.android.evaluacion1crisjerry

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.crisjrrbndhn.android.evaluacion1crisjerry.Restaurante.PedidoManager
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var etCantidad: EditText
    private lateinit var etCantidad2: EditText
    private lateinit var tvComidaTotal: TextView
    private lateinit var switchPropina: Switch
    private lateinit var tvTotalFinal: TextView
    private lateinit var pedidoManager: PedidoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCantidad = findViewById<EditText>(R.id.etCantidad)
        etCantidad2 = findViewById<EditText>(R.id.etCantidad2)
        tvComidaTotal = findViewById<TextView>(R.id.tvComidaTotal)
        switchPropina = findViewById<Switch>(R.id.switchPropina)
        tvTotalFinal = findViewById<TextView>(R.id.tvTotalFinal)
        pedidoManager = PedidoManager()

        etCantidad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    pedidoManager.actualizarCantidadPastelChoclo(s.toString().toInt())
                    actualizarTotal()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        etCantidad2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    pedidoManager.actualizarCantidadCazuela(s.toString().toInt())
                    actualizarTotal()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            actualizarTotal(isChecked)
        }
    }

    private fun actualizarTotal(aplicarPropina: Boolean = switchPropina.isChecked) {
        val totalComida = pedidoManager.calcularTotal().toDouble()
        val propina = if (aplicarPropina) totalComida * 0.1 else 0.0
        val totalConPropina = totalComida + propina

        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        formatoMoneda.maximumFractionDigits = 0

        val totalSinPropina = totalComida

        val totalConPropinaFormateado = formatoMoneda.format(totalConPropina)
        val totalSinPropinaFormateado = formatoMoneda.format(totalSinPropina)

        tvTotalFinal.text = "Total: $totalConPropinaFormateado"
        tvComidaTotal.text = "Comida: $totalSinPropinaFormateado"
    }
}