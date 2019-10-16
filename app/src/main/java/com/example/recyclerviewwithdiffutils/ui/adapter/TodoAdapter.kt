package com.example.recyclerviewwithdiffutils.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithdiffutils.R
import com.example.recyclerviewwithdiffutils.model.Todo

class TodoAdapter(): RecyclerView.Adapter<TodoAdapter.ViewHolder>() ,Filterable{


    private var todoList = ArrayList<Todo>()
    private var todoListContainFiltered = ArrayList<Todo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.todo_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(ratings:List<Todo>){
        todoList.clear()
        todoList.addAll(ratings)
        todoListContainFiltered.addAll(ratings)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_userId.text = todoList.get(position).id.toString()
        holder.tv_title.text = todoList.get(position).title
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv_userId = itemView.findViewById<TextView>(R.id.tv_userId)
        val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
    }

    override fun getFilter(): Filter {
        return object:Filter(){
            private var todoListFiltered = ArrayList<Todo>()
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSequenceString = p0.toString()
                if(charSequenceString.isEmpty()){
                    todoListFiltered.addAll(todoListContainFiltered)
                }else{
                    Log.d("size==>","${todoList.size}")
                    for(item in todoListContainFiltered){
                        if(item.title.toLowerCase().contains(charSequenceString.toLowerCase())){
                            Log.d("title=>${item.id}",item.title)
                            todoListFiltered.add(item)
                        }

                    }
                }
                val filterResults = FilterResults()
                filterResults.values = todoListFiltered
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                todoList.clear()
                todoList.addAll(p1?.values as List<Todo>)
                notifyDataSetChanged()
            }

        }
    }

}