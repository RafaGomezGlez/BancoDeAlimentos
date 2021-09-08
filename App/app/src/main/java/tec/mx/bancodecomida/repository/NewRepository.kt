package tec.mx.bancodecomida.repository
import tec.mx.bancodecomida.model.New


class NewRepository {
    fun getAllData(): List <New>{
        return listOf(
            New(
                id = 0,
                firstName = "John",
                lastName = "Doe",
                age = 20
            ),
            New(
                id = 1,
                firstName = "Maria",
                lastName = "Garcia",
                age = 21
            ),
            New(
                id = 2,
                firstName = "James",
                lastName = "Johnson",
                age = 22
            ),
            New(
                id = 3,
                firstName = "Michael",
                lastName = "Brown",
                age = 23
            ),
            New(
                id = 4,
                firstName = "Robert",
                lastName = "Davis",
                age = 24
            ),
            New(
                id = 5,
                firstName = "Jenifer",
                lastName = "Miller",
                age = 25
            ),
            New(
                id = 6,
                firstName = "Sarah",
                lastName = "Lopez",
                age = 26
            ),
            New(
                id = 7,
                firstName = "Charles",
                lastName = "Wilson",
                age = 27
            ),
            New(
                id = 8,
                firstName = "Daniel",
                lastName = "Taylor",
                age = 28
            ),
            New(
                id = 9,
                firstName = "Mark",
                lastName = "Lee",
                age = 29
            ),
        )
    }
}