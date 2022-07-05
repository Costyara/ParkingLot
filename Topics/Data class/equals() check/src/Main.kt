data class Client(val name: String, val age: Int, val balance: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (name != other.name) return false
        if (age != other.age) return false
        //if (balance != other.balance) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        result = 31 * result + balance
        return result
    }
}

fun main() {
    val client1 = Client(readln(), readln().toInt(), readln().toInt())
    val client2 = Client(readln(), readln().toInt(), readln().toInt())
    println(client1 == client2)
}