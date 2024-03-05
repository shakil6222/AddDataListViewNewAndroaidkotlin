package com.example.adddatalistviewnew

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class User_Adadpter(val context: Context, var userlist: ArrayList<User_Model>) :
    RecyclerView.Adapter<User_Adadpter.Myholder>() {


    class Myholder(itemview: View) : ViewHolder(itemview) {

        var names = itemview.findViewById<TextView>(R.id.mTitle_TextView)
        var number = itemview.findViewById<TextView>(R.id.nSubTitle_TextView)

        //        delete id toolBar
        val toolbar = itemview.findViewById<Toolbar>(R.id.list_toolbar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myholder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.list_itom, parent, false)
        return Myholder(layout)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    fun searchResult(searchData: List<User_Model>) {
        userlist = searchData as ArrayList<User_Model>
        notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged", "MissingInflatedId")
    override fun onBindViewHolder(holder: Myholder, position: Int) {
        holder.names.text = userlist[position].name
        holder.number.text = userlist[position].number


//        delete queary
        holder.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.delete_Menu -> {
                    userlist.removeAt(position)
                    notifyDataSetChanged() // Notify adapter of data change
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                    true // Indicate that the click event was handled
                }


//    Update Queary in menu bar item
                R.id.update_Menu -> {

                    val layout =
                        LayoutInflater.from(context).inflate(R.layout.add_itom, null, false)
                    val editTextName = layout.findViewById<EditText>(R.id.userName_EditText)
                    val number: EditText = layout.findViewById(R.id.number_EditText)
                    val button: AppCompatButton = layout.findViewById(R.id.submit)
                    val dialog = AlertDialog.Builder(context).create()
                    dialog.setView(layout)

                    editTextName.setText(userlist[position].name)
                    number.setText(userlist[position].number)


                    button.setOnClickListener {
                        userlist[position] =
                            User_Model(editTextName.text.toString(), number.text.toString())
                        notifyDataSetChanged()
                        Toast.makeText(context, "item Update", Toast.LENGTH_SHORT).show()

                        dialog.dismiss()
                    }
                    dialog.show()

                    true
                }

                else -> false // Indicate that the click event was not handled
            }
        }

    }
}