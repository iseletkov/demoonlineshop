package ru.studyit.demoonlineshop.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name                                 = "users")
class CUser(
    @Id
    @GenericGenerator(
        name                                = "UUIDGenerator",
        strategy                            = "uuid2"
    )
    @GeneratedValue(
        generator                           = "UUIDGenerator"
    )
    @Column(
        name                                = "id"
    )
    var id                                  : UUID?
                                            = null,

    @Column(
        name                                = "sex"
    )
    var sex                                 : Boolean
                                            = true,


    @Column(
        name                                = "login"
    )
    var login                               : String
                                            = "",

    @Column(
        name                                = "date_of_birth",
        columnDefinition                    = "DATE"
    )
    var dateOfBirth                         : LocalDate?
                                            = null,



    @OneToMany(
        mappedBy                            = "owner",
        fetch                               = FetchType.EAGER
    )
    var orders                              : MutableList<COrder>
                                            = mutableListOf()
)

