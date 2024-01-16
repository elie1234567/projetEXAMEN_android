package com.example.examenandroide

import java.util.Random

class crudclass (
    var id: Int = 0,  // Ne pas utiliser getI() par défaut
    var Nom: String = "",
    var Genre: String = "",
    var Datenss: String = "",
    var Age: String = ""
) {
    companion object {
        private var idCounter = 0

        fun getUniqueID(): Int {
            return idCounter++
        }
    }
}

