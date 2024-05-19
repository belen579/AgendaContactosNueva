package com.example.ejemplo4_calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ejemplo4_calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var resultado: Double = 0.0
    var operandouno:Double =0.0
    var operandodos:Double =0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.grupodeoperaciones.setOnCheckedChangeListener(){_,checkedId ->

                when(checkedId){
                    binding.suma.id-> Toast.makeText(this, "Ha marcado suma", Toast.LENGTH_SHORT).show()
                    binding.resta.id->  Toast.makeText(this, "Ha marcado Resta", Toast.LENGTH_SHORT).show()
                    binding.divi.id->  Toast.makeText(this, "Ha marcado División", Toast.LENGTH_SHORT).show()
                    binding.multi.id->  Toast.makeText(this, "Ha marcado Multiplicación ", Toast.LENGTH_SHORT).show()
                }




            }

        binding.calcular.setOnClickListener(){

            if(binding.suma.isChecked){
                operandouno= binding.operandouno.text.toString().toDoubleOrNull()!!
                operandodos = binding.operandodos.text.toString().toDoubleOrNull()!!
                resultado= operandouno + operandodos
                binding.screen.text= binding.screen.text.toString()+"\n $operandouno + $operandodos = $resultado"


            }
            if(binding.resta.isChecked){
                operandouno= binding.operandouno.text.toString().toDoubleOrNull()!!
                operandodos = binding.operandodos.text.toString().toDoubleOrNull()!!
                resultado= operandouno - operandodos
                binding.screen.text= binding.screen.text.toString()+"\n $operandouno - $operandodos = $resultado"


            }
            if(binding.multi.isChecked){
                operandouno= binding.operandouno.text.toString().toDoubleOrNull()!!
                operandodos = binding.operandodos.text.toString().toDoubleOrNull()!!
                resultado= operandouno * operandodos
                binding.screen.text= binding.screen.text.toString()+"\n $operandouno * $operandodos = $resultado"


            }
            if(binding.divi.isChecked){
                operandouno= binding.operandouno.text.toString().toDoubleOrNull()!!
                operandodos = binding.operandodos.text.toString().toDoubleOrNull()!!
                resultado= operandouno / operandodos
                binding.screen.text= binding.screen.text.toString()+"\n $operandouno / $operandodos = $resultado"


            }



        }

        binding.borrar.setOnClickListener(){

            binding.operandouno.text=null
            binding.operandodos.text = null
            binding.screen.text = null
            resultado =0.0
            operandodos =0.0
            operandodos =0.0


        }






    }
}


