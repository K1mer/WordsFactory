package com.example.wordsfactory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsfactory.domain.Definition

class DefinitionAdapter(context: Context?, definitions: ArrayList<Definition>) : RecyclerView.Adapter<DefinitionAdapter.ViewHolder>() {
    private val inflater: LayoutInflater
    private val definitions: ArrayList<Definition>

    init {
        this.definitions = definitions
        inflater = LayoutInflater.from( context )
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ViewHolder {
        val view: View = inflater.inflate( R.layout.definition_list_item, parent, false )
        return ViewHolder( view )
    }

    override fun onBindViewHolder( holder: ViewHolder, position: Int ) {
        val definition: Definition = definitions[ position ]
        holder.meaningTextView.text = definition.definition

        if( definition.example.isNullOrEmpty() ) {
            holder.exampleLayout.visibility = View.GONE
        } else {
            holder.exampleTextView.text = definition.example
        }
    }

    override fun getItemCount(): Int {
        return definitions.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val meaningTextView: TextView
        val exampleTextView: TextView
        val exampleLayout: ConstraintLayout

        init {
            meaningTextView = view.findViewById( R.id.meaningText )
            exampleTextView = view.findViewById( R.id.exampleText )
            exampleLayout = view.findViewById( R.id.exampleLayout )
        }
    }
}