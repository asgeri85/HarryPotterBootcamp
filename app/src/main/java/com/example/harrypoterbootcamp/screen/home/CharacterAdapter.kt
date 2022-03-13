package com.example.harrypoterbootcamp.screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypoterbootcamp.databinding.CardCharacterBinding
import com.example.harrypoterbootcamp.model.CharacterModel

class CharacterAdapter :ListAdapter<CharacterModel,CharacterAdapter.CharacterViewHolder>(DiffCallback){

    var onClick:(CharacterModel)->Unit={}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val cardLayout=CardCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CharacterViewHolder(cardLayout)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character=getItem(position)
        holder.bind(character,onClick)
    }

    class CharacterViewHolder(private val cardCharacterBinding: CardCharacterBinding):RecyclerView.ViewHolder(cardCharacterBinding.root){

        fun bind(characterModel: CharacterModel,onClick:(CharacterModel)->Unit={}){
            cardCharacterBinding.character=characterModel
            cardCharacterBinding.executePendingBindings()
            cardCharacterBinding.card.setOnClickListener {
                onClick(characterModel)
            }
        }
    }

    companion object DiffCallback:DiffUtil.ItemCallback<CharacterModel>(){
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem==newItem
        }

    }
}