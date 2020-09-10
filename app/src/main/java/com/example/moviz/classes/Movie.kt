package com.example.moviz.classes

import com.example.moviz.R
import kotlin.properties.Delegates

class Movie(val title:String, val thumbnail:String, val coverPhoto:String, val description:String, val studio:String, val rating:String, val streamLink:String){
    constructor():this("","https://square.github.io/picasso/static/sample.png","https://square.github.io/picasso/static/sample.png","","","","")
}
