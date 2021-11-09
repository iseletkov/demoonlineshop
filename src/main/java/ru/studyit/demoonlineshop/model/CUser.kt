package ru.studyit.demoonlineshop.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.Period
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class CUser (
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    var id : UUID? = null,

    @Column(name = "sex")
    var sex : Boolean = true,

    @Column(name = "login")
    var login: String? = null,

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    var dateOfBirth: LocalDate = LocalDate.now(),



    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    var orders: MutableList<COrder> = mutableListOf()
)
{
    val age: Int get() = Period.between(dateOfBirth, LocalDate.now()).years

//    fun getAge(): Int {
//        val now = LocalDate.now()
//        return now.year - dateOfBirth.year
//    }
//
//    fun getAge(date: LocalDate?): Int {
//        val now = LocalDate.now()
//        return if (date == null) now.year else now.year - date.year
//    }
}
