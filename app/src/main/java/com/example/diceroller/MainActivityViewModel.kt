package com.example.diceroller

import android.content.Intent
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel(){
    private var isPermissionGiven = ""
    private var accesURI: Uri? = null;

    fun setURI(uri: Uri){
       this.accesURI = uri
    }
    fun getUri (): Uri?{
        return accesURI;
    }
    fun getUriString (): String{
        return accesURI?.toString().toString()
    }


}