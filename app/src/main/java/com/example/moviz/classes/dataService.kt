package com.example.moviz.classes

import com.example.moviz.R

class dataService{
companion object{
    fun getMoviesList():ArrayList<Movie>{
        val lstMovies=ArrayList<Movie>()
        lstMovies.add(
            Movie(
                "Janatha Garage",
                R.drawable.anatha,
                R.drawable.anatha2
            )
        )
        lstMovies.add(
            Movie(
                "Geetha Govindham",
                R.drawable.gee,
                R.drawable.gee2
            )
        )
        lstMovies.add(
            Movie(
                "Ala Vaikuntapuramulo",
                R.drawable.ala,
                R.drawable.ala2
            )
        )
        lstMovies.add(
            Movie(
                "Srimanthudu",
                R.drawable.sri,
                R.drawable.sri2
            )
        )
        return lstMovies
    }

}
}