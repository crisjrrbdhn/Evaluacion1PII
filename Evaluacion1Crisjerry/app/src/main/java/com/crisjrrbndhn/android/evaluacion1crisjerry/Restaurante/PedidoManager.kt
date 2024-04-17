package com.crisjrrbndhn.android.evaluacion1crisjerry.Restaurante

class PedidoManager {
    private val PRECIO_PASTEL_CHOCLO = 12000
    private val PRECIO_CAZUELA = 10000
    private var cantidadPastelChoclo = 0
    private var cantidadCazuela = 0

    fun actualizarCantidadPastelChoclo(cantidad: Int) {
        cantidadPastelChoclo = cantidad
    }

    fun actualizarCantidadCazuela(cantidad: Int) {
        cantidadCazuela = cantidad
    }

    fun calcularTotal(): Int {
        val totalPastelChoclo = cantidadPastelChoclo * PRECIO_PASTEL_CHOCLO
        val totalCazuela = cantidadCazuela * PRECIO_CAZUELA
        return totalPastelChoclo + totalCazuela
    }
}