package com.example.adddatalistviewnew

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Locale


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adpater: User_Adadpter
    private val userlist = ArrayList<User_Model>()

    lateinit var addButton: FloatingActionButton
    lateinit var searchView: SearchView


    //search View KA Text
    lateinit var nodata: TextView
    lateinit var backButton: ImageView
    private lateinit var speakvoice:ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = (Color.GREEN)

        recyclerView = findViewById(R.id.mRecycler_RecycleView)
        addButton = findViewById(R.id.addingBtn_FlottingButton)
        backButton = findViewById(R.id.backButton_ImageView)

        searchView = findViewById(R.id.idBaseSearch_SearchView)
        nodata = findViewById(R.id.noData_textView)

        speakvoice = findViewById(R.id.speak_ImageView)


        val imageLogo = findViewById<ImageView>(R.id.imageLogo_ImageView)

        speakvoice.setOnClickListener {
            voicefunction()
        }

        imageLogo.setOnClickListener {
            Toast.makeText(this, "My Home Tiwision Name", Toast.LENGTH_SHORT).show()
        }
//

        backButton.setOnClickListener {
            onBackPressed()
            Toast.makeText(this, "Back Home", Toast.LENGTH_SHORT).show()
        }
        userlist.add(User_Model("Shakil Ansari", "6206731127"))
        userlist.add(User_Model("MD Wakil Ansari", "9661101556"))
        userlist.add(User_Model("Rausan Kumar", "......"))
        userlist.add(User_Model("Rausani Kumari", "......"))
        userlist.add(User_Model("Sonam Kumari", " 10/12/2023"))
        userlist.add(User_Model("Indrjit Kumar", "10/01/2024"))
        userlist.add(User_Model("Gunga Kumari","10/01/2024"))
        userlist.add(User_Model("Pawan Kumar", "9162699212"))
        userlist.add(User_Model("Kawlesh Kumar", "6287713326"))
        userlist.add(User_Model("Sanoj Kumar", "9955827277"))
        userlist.add(User_Model("Sandeep Kumar", "7479797523"))
        userlist.add(User_Model("Pintu Kumar", "10/11/2023"))
        userlist.add(User_Model("Anshu Kumar", "10/01/1024"))

        adpater = User_Adadpter(this, userlist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adpater

        addButton.setOnClickListener {
            addInform()
        }
        //search View
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            @SuppressLint("SuspiciousIndentation")
            override fun onQueryTextChange(newText: String?): Boolean {
                val searchData = ArrayList<User_Model>()
//                     if (newText?.isNotEmpty() == true){
                    if (newText?.isNotEmpty() == true) {
                        for (search in userlist) {
                            if (search.name.lowercase(Locale.ROOT).contains(newText.lowercase()) || search.name.uppercase().contains(newText)
                            ) {
                                searchData.add(search)
                            }
                            else{
                                Toast.makeText(this@MainActivity, "No Data Found",Toast.LENGTH_SHORT).show()
                            }

                        }
                        if (searchData.isEmpty()) {
                            adpater.searchResult(searchData)
                            nodata.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE

                        } else {
                            adpater.searchResult(searchData)
                            nodata.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE

                        }

                    } else {
                        adpater.searchResult(searchData)
                        nodata.visibility = View.GONE
                         recyclerView.visibility = View.VISIBLE
                    }
//                }
                return true
            }

        })

    }

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    private fun addInform() {
        val dialog = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.add_itom, null, false)

        val nameUsre : EditText = view.findViewById(R.id.userName_EditText)
        val userNumber : EditText = view.findViewById(R.id.number_EditText)

//        val dateUser : EditText = view.findViewById(R.id.date_EditView)
//        val imageBt :ImageView = view.findViewById(R.id.addImage_ImageView)

//       Custom Alert Dialog code
        var submitButton: AppCompatButton = view.findViewById(R.id.submit)

        val alert = dialog.create()
        alert.setView(view)
        alert.show()

        submitButton.setOnClickListener {
            userlist.add(User_Model(nameUsre.text.toString(), userNumber.text.toString()))

            adpater.notifyDataSetChanged()
            alert.dismiss()

//         imageBt.setOnClickListener {
//             cheakGalleryPermission()
//         }
        }
    }

//     fun speakImageFun(){
//        val dialog = AlertDialog.Builder(this)
//        val layout = LayoutInflater.from(this).inflate(R.layout.speak_layoutdilog,null,false)
//
//        val okButton :TextView = layout.findViewById(R.id.okSpeak_textview)
//        val alert = dialog.create()
//        alert.setView(layout)
//        okButton.setOnClickListener {
//            alert.dismiss()
//        }
//    }

    fun voicefunction(){

        val dilogBox = AlertDialog.Builder(this)
        val layoutVoice = LayoutInflater.from(this).inflate(R.layout.speak_layoutdilog,null,false)
        val alert = dilogBox.create()

       alert.setView(layoutVoice)

       alert.show()
    }

//    fun cheakGalleryPermission(){
//        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
//            PackageManager.PERMISSION_GRANTED &&
//        ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
//                PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(this,"Gallery Permission Granted",Toast.LENGTH_SHORT).show()
//            openGallery()
//        }
//        else{
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE ,
//            android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                786)
//        }
//    }
//    fun openGallery(){
//        val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
//        startActivityForResult(intent,786)
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
//            PackageManager.PERMISSION_GRANTED &&
//            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
//            PackageManager.PERMISSION_GRANTED){
////            Toast.makeText(this,"Gallery Permission Granted",Toast.LENGTH_SHORT).show()
//            openGallery()
//        }
//        else{
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE ,
//                android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                786)
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 786 && resultCode == RESULT_OK){
//            val imageUri = data?.extras?.get("gallery") as Bitmap
//            addButton.setImageBitmap(imageUri)
//
//        }
//    }
//


}




