package ch.heigvd.iict.sym.labo1.data

interface IUserRepository {
    fun findAll(): List<Pair<String, String>>
    fun getByEmail(email: String): Pair<String, String>
    fun save(user: Pair<String, String>)
}