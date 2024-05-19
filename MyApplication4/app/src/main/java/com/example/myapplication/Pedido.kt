class Pedido {

    open class Item(val nombre: String, val precio: Double) {
        override fun toString(): String {
            return "$nombre: $$precio"
        }
    }

    class BasePizza : Item("BasePizza", 10.0)


    class Ingrediente {

        var nombreingrediente: String = ""
        var numerodeingredientes: Int=0
        val precioingrediente: Double =5.0


        constructor( nombreingrediente: String, numerodeingredientes:Int)  {
            this.nombreingrediente = nombreingrediente
            this.numerodeingredientes= numerodeingredientes
        }

        override fun toString(): String {
            return "Ingrediente(Nombre de los ingredientes='$nombreingrediente', numerodeingredientes=$numerodeingredientes, precioingrediente=$precioingrediente)"
        }

    }


    class pedido(var numero: Int= 0, val elementos: List<Item>, val ingrediente: List<Ingrediente>) {
        var total:Double=0.0
        fun calcularingredientes(): Double {
            for (i in ingrediente){
                var precioingredientes=  i.numerodeingredientes*i.precioingrediente
                return precioingredientes
            }


            return TODO("Provide the return value")
        }
        fun calcularTotal(): Double {

            for (e in elementos){
                total = e.precio+ calcularingredientes()
            }
            return total

        }

        override fun toString(): String {
            val pedidoString = StringBuilder()



            for (elemento in elementos) {


                pedidoString.append(elemento.toString()).append("\n")
            }
            for(i in ingrediente){



                pedidoString.append(ingrediente.toString()).append("\n")


            }
            pedidoString.append("Total Precio de los ingredientes : $${calcularingredientes()}")
            pedidoString.append(" \n Total Pedido: $${calcularTotal()}")
            return pedidoString.toString()

        }
    }

}