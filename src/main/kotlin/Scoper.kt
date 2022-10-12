import models.Person

class Scoper {

    fun scope() {
        letScopeDemo()
        withScopeDemo()
        runScopeDemo()
        applyScopeDemo()
        alsoScopeDemo()
        takeIfAndTakeUnless()
    }

    private fun takeIfAndTakeUnless() {

        val number = System.currentTimeMillis()

        val oddOrNull = number.takeIf {
            it % 2 == 1L
        }
        val evenOrNull = number.takeIf {
            it % 2 == 0L
        }
        println("takeIfAndTakeUnless $oddOrNull $evenOrNull")

        //takeIfAndTakeUnless null 1665614976194
    }

    private fun letScopeDemo() {
        Person("Alice", 20, "Bangor").let {
            println(it)
            it.moveTo("Belfast")
            it.incrementAge()
            println(it)
        }

        val personList = mutableListOf<Person>(
            Person("Bob", 22, "Adelaide"),
            Person("Kate", 23, "Bangor"),
            Person("Blackadder", 24, "Belfast")
        )

        personList
            .filter{ it.age >22 }
            .let{ println("letScopeDemo 1 $it") }

        personList
            .let(::println)

        val s: String? = "Bobbins" //could be null
        s?.let {
            println("letScopeDemo 2 $it")
        }

        val capitalFirst = personList.first().let {
            it.name.uppercase()
        }
        println("letScopeDemo 3 $capitalFirst")


//        Person(name=Alice, age=20, location=Bangor)
//        Person(name=Alice, age=21, location=Belfast)
//        letScopeDemo 1 [Person(name=Kate, age=23, location=Bangor), Person(name=Blackadder, age=24, location=Belfast)]
//        [Person(name=Bob, age=22, location=Adelaide), Person(name=Kate, age=23, location=Bangor), Person(name=Blackadder, age=24, location=Belfast)]
//        letScopeDemo 2 Bobbins
//        letScopeDemo 3 BOB
    }



    private fun withScopeDemo() {
        val personList = mutableListOf<Person>(
            Person("Bob", 22, "Adelaide"),
            Person("Kate", 23, "Bangor"),
            Person("Blackadder", 24, "Belfast")
        )

        with(personList){
            println("withScopeDemo 1 $size")
            this.sortBy {
                it.name
            }
            println("withScopeDemo 2 $size")
        }

        val result = with(personList){
            "first ${this.first()}"
        }
        println("withScopeDemo 3 $result")

//        withScopeDemo 1 3
//        withScopeDemo 2 3
//        withScopeDemo 3 first Person(name=Blackadder, age=24, location=Belfast)
   }

    private fun runScopeDemo() {
        val personList = mutableListOf<Person>(
            Person("Bob", 22, "Adelaide"),
            Person("Kate", 23, "Bangor"),
            Person("Blackadder", 24, "Belfast")
        )

        personList.run {
            println("runScopeDemo 1 $size")
        }

        val result = personList.run{
            "first ${this.first()}"
        }
        println("runScopeDemo 2 $result")

        //runScopeDemo 1 3
        //runScopeDemo 2 first Person(name=Bob, age=22, location=Adelaide)
    }



    private fun applyScopeDemo() {
        val bob = Person("Bob", 56, "Bobbins")
            .apply {
                println("applyScopeDemo 1 $this")

                moveTo("Mighty Bobbins")
            }
        println("applyScopeDemo 2 $bob")

//        applyScopeDemo 1 Person(name=Bob, age=56, location=Bobbins)
//        applyScopeDemo 2 Person(name=Bob, age=56, location=Mighty Bobbins)
    }

    private fun alsoScopeDemo() {
        val personList = mutableListOf<Person>(
            Person("Bob", 22, "Adelaide"),
            Person("Kate", 23, "Bangor"),
            Person("Blackadder", 24, "Belfast")
        )

        val bob = personList
            .also {
                println("alsoScopeDemo 1 ${it.size}")
            }
        println("alsoScopeDemo 2 $bob")

//        alsoScopeDemo 1 3
//        alsoScopeDemo 2 [Person(name=Bob, age=22, location=Adelaide), Person(name=Kate, age=23, location=Bangor), Person(name=Blackadder, age=24, location=Belfast)]

    }

}