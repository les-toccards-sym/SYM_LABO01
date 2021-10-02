package ch.heigvd.iict.sym.labo1.data

class InMemoryUserRepository : IUserRepository {

    // on définit une liste de couples e-mail / mot de passe
    // ceci est fait juste pour simplifier ce premier laboratoire,
    // mais il est évident que de hardcoder ceux-ci est une pratique à éviter à tout prix...
    // /!\ listOf() retourne une List<T> qui est immuable
    private var users: List<Pair<String, String>> = listOf(
        Pair("user1@heig-vd.ch","1234"),
        Pair("user2@heig-vd.ch","abcd")
    )

    override fun findAll(): List<Pair<String, String>> = this.users

    override fun save(user: Pair<String, String>) {
        this.users = this.users + user
    }

}