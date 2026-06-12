package com.orbitalsonic.bottomnavsample.presentation.extras.language.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orbitalsonic.bottomnavsample.R
import com.orbitalsonic.bottomnavsample.databinding.ItemLanguageBinding
import com.orbitalsonic.bottomnavsample.presentation.extras.language.model.LanguageItem

class AdapterLanguage(
    private val onItemClick: ((LanguageItem) -> Unit)? = null
) : ListAdapter<LanguageItem, AdapterLanguage.LanguageViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageViewHolder {

        val binding = ItemLanguageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class LanguageViewHolder(
        private val binding: ItemLanguageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LanguageItem) = with(binding) {

            mtvLanguageName.text = item.languageFullName
            ivFlag.setImageResource(item.flag)

            val context = root.context

            if (item.selected) {

                langItem.setBackgroundResource(R.drawable.bg_lang_selected)

                ivLangChecked.setImageResource(
                    R.drawable.ic_lang_radio_checked
                )

                mtvLanguageName.setTextColor(
                    ContextCompat.getColor(context, R.color.primary500)
                )

            } else {

                langItem.setBackgroundResource(R.drawable.bg_lang_unselected)

                ivLangChecked.setImageResource(
                    R.drawable.ic_lang_radio_unchecked
                )

                mtvLanguageName.setTextColor(
                    ContextCompat.getColor(context, R.color.onCard)
                )
            }

            langItem.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    companion object {

        private val DiffCallback =
            object : DiffUtil.ItemCallback<LanguageItem>() {

                override fun areItemsTheSame(
                    oldItem: LanguageItem,
                    newItem: LanguageItem
                ): Boolean {

                    // Unique identifier
                    return oldItem.languageCode == newItem.languageCode
                }

                override fun areContentsTheSame(
                    oldItem: LanguageItem,
                    newItem: LanguageItem
                ): Boolean {

                    return oldItem == newItem
                }
            }
    }
}