package com.netanel.hometest.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.netanel.hometest.databinding.ItemCharacterBinding

import com.netanel.hometest.home.model.character.Character

/**
 * Created by netanelamar on 01/01/2024.
 * NetanelCA2@gmail.com
 */
class CharactersAdapter :
    ListAdapter<Character, CharactersAdapter.CharacterViewHolder>(CharacterComparator()) {
    private lateinit var binding: ItemCharacterBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val listOfCharacters = getItem(position)
        holder.bind(listOfCharacters)
    }

    class CharacterViewHolder(binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val titleText = binding.titleText
        private val subTitleText = binding.subTitleText
        private val image = binding.image
        private val moreDataText = binding.moreDataText

        fun bind(character: Character) {
            titleText.text = character.name.ifEmpty { "N/A" }
            subTitleText.text = character.status.ifEmpty { "N/A" }
            Glide.with(image.context).load(character.image).into(image)
            moreDataText.text =
                "Gender: ${character.gender.ifEmpty { "N/A" }}\nSpecies: ${character.species.ifEmpty { "N/A" }}\nOrigin: ${character.origin.name.ifEmpty { "N/A" }}\nLocation: ${character.location.name.ifEmpty { "N/A" }}\nType: ${character.type.ifEmpty { "N/A" }}"
        }
    }

    class CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}

