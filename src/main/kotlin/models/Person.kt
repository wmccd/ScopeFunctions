package models

data class Person(val name: String, var age: Int, var location:String){
    fun moveTo(newLocation: String){
        this.location = newLocation
    }
    fun incrementAge(){
        this.age++
    }
}
