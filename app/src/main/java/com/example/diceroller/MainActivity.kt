 package com.example.diceroller

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

 class MainActivity : AppCompatActivity() {
     private val itemsList = ArrayList<ItemData>()
     private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Demo"

        //ViewModel: To save and load data when the UI is destroyed and recreated.
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //Buttons and Texts
        val permissionBtn:Button = findViewById(R.id.get_permission_btn)
        val writeBtn: Button = findViewById(R.id.write_btn)
        val listBtn: Button = findViewById(R.id.list_button)
        val uriText: TextView = findViewById(R.id.URI_textview)

        //Scrollable View
        val recyclerView:RecyclerView = findViewById(R.id.recycler_view)
        customAdapter= CustomAdapter(itemsList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter


        fun listEverythingInDocument(){
            val test = viewModel.getUri()?.let { it1 -> DocumentFile.fromTreeUri(this, it1) }
            if (test != null) {
                for (file in test.listFiles()) {
                    if (file.isDirectory) { // if it is sub directory
                        file.name?.let {
                            itemsList.add(ItemData(true, it))
                        }

                    } else {
                        file.name?.let {
                            itemsList.add(ItemData(false, it))
                        }
                    }
                }
                customAdapter.notifyDataSetChanged()
            }
        }

        //Intent to get folder URI
        val getURI = registerForActivityResult(ActivityResultContracts.OpenDocumentTree()){
            viewModel.setURI(it)
            uriText.text = it.toString()
            //clear list once a new directory has been selected
            itemsList.clear()
            listEverythingInDocument()
        }



        //Get the saved URI and display it
        uriText.text = viewModel.getUriString()

        //Listeners
        permissionBtn.setOnClickListener {
            getURI.launch(null)
        }

        writeBtn.setOnClickListener {

        }

        listBtn.setOnClickListener {
            listEverythingInDocument()
        }


    }
}
