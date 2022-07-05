fun main() {
    fun throwIfNull(name: String?) {
        if (name == null){
            throw Exception("Name can't be null!")
        }
    }
    throwIfNull("null")
}