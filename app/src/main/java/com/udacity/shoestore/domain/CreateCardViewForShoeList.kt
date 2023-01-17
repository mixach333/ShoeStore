package com.udacity.shoestore.domain

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.udacity.shoestore.models.Shoe

class CreateCardViewForShoeList {
    operator fun invoke(shoe: Shoe, context: Context): CardView {
        val cardView = CardView(context).apply {
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            val margin = 8
            params.setMargins(margin, margin, margin, margin)
            layoutParams = params
            radius = 15f
            setPadding(25, 25, 25, 25)
            cardElevation = 10f

        }
        val linearLayout = LinearLayout(context).apply {
            ViewGroup.LayoutParams.MATCH_PARENT
            ViewGroup.LayoutParams.MATCH_PARENT
            orientation = LinearLayout.VERTICAL
            setPadding(16, 4, 4, 16)
        }
        val textViewName = TextView(context).apply {
            text = shoe.name
            textSize = 16f
        }
        val textViewSize = TextView(context).apply {
            var result = if (shoe.size % 1 > 0) {
                shoe.size.toString()
            } else {
               shoe.size.toInt().toString()
            }
            text = result
            textSize = 16f
        }
        val textViewCompany = TextView(context).apply {
            text = shoe.company
            textSize = 16f
        }
        val textViewDescription = TextView(context).apply {
            text = shoe.description
            textSize = 16f
        }
        linearLayout.apply {
            addView(textViewName)
            addView(textViewSize)
            addView(textViewCompany)
            addView(textViewDescription)
        }
        cardView.addView(linearLayout)
        return cardView
    }
}