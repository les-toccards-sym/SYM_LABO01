/**
 * @author Yohann Polus <yohann.polus@heig-vd.ch>
 * @author Melvin Merk <melvin.merk@heig-vd.ch>
 * @author Doran Kayoumi <doran.kayoumi@heig-vd.ch>
 */

package ch.heigvd.iict.sym.labo1.data

interface IUserRepository {
    fun findAll(): List<Pair<String, String>>
    fun findByEmailAndPasswd(email: String, passwd: String): Boolean

    fun save(user: Pair<String, String>)
}