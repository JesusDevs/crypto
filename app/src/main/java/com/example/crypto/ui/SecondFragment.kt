package com.example.crypto.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.crypto.R
import com.example.crypto.databinding.FragmentSecondBinding
import com.example.crypto.viewmodel.ViewModel
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val viewModel: ViewModel by activityViewModels()

    private var idCrypto: String = ""
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            idCrypto = it.getString("id","")
        }



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.selectedItem().observe(viewLifecycleOwner, {
            it?.let {

                viewModel.getDetailCryptoById(it.id).observe(viewLifecycleOwner, {

                    binding.name.text = "NAME : " + it.name
                    binding.precio.text = "PRICE : " + it.price
                    binding.rank.text = "RANK : " + it.rank
                    binding.symbol.text = "SYMBOL : " + it.symbol
                    binding.price.text = "PRICE DATE : " + it.priceDate
                    binding.currency.text = "CURRENCY : " + it.currency
                    binding.status.text = "STATUS : " + it.status
                    binding.rankDelta.text = "RANK DELTA : " + it.rankDelta

                    binding.imageViewLogo.loadSvg(it.logoUrl)

                })
                val name = it.name
                binding.btnShare.setOnClickListener {
                    sendMailCrypto(name)
                }
            }
        })


        }

    fun sendMailCrypto (name :String) {
        //crear mail de envio
        val email = arrayOf("Info@cryptoinvest.cl")
        //obtener el texto desde una variable
        val message = " Hola\n" +
                "Quisiera pedir información sobre esta moneda $name, me gustaría que me contactaran a\n" +
                "este correo o al siguiente número : _________\n" +
                "Quedo atento"
        //intent to share the text
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.data = Uri.parse("mailto:")
        shareIntent.type = "text/plain"

        shareIntent.putExtra(Intent.EXTRA_EMAIL,email)
        shareIntent.putExtra(Intent.EXTRA_TEXT,message  )
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Consulto por $name")
       // crear menu de opciones para compartir segun app instalada
        startActivity(Intent.createChooser(shareIntent,"Share To  : "))

        try {
            startActivity(shareIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "e.message", Toast.LENGTH_LONG).show()
        }

    }
}