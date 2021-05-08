package com.example.crypto.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crypto.databinding.ActivityMainBinding
import com.example.crypto.databinding.CryptoListBinding
import com.example.crypto.pojo.CryptoResponseItem

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoVH>() {

    private var listCryptoItem = listOf<CryptoResponseItem>()
    private var selectedItem = MutableLiveData<CryptoResponseItem>()
    fun selectedItem()=selectedItem


    fun updateList (list: List<CryptoResponseItem>){

        listCryptoItem=list
        notifyDataSetChanged()

    }

    inner class CryptoVH(private val binding: CryptoListBinding) :
            RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        fun bind(cryptoResponseItem: CryptoResponseItem){
            binding.name.text = cryptoResponseItem.name
            binding.precio.text = cryptoResponseItem.price
            binding.symbol.text = cryptoResponseItem.symbol
            binding.rank.text = cryptoResponseItem.rank
            binding.price.text = cryptoResponseItem.priceDate


            Glide.with(binding.imageViewLogo)
                .load(cryptoResponseItem.logoUrl)
                .centerInside()
                .into(binding.imageViewLogo)
            itemView.setOnClickListener ( this )

        }




        override fun onClick(v: View?) {
            selectedItem.value= listCryptoItem[adapterPosition]
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoVH {
        return CryptoVH(CryptoListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CryptoVH, position: Int) {
val cryptoResponseItem = listCryptoItem[position]
        holder.bind(cryptoResponseItem)
    }

    override fun getItemCount(): Int = listCryptoItem.size

}