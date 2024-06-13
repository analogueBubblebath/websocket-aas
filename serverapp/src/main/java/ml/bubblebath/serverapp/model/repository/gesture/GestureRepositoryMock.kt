package ml.bubblebath.serverapp.model.repository.gesture

class GestureRepositoryMock(private val datasource: Datasource) : GestureRepository {
    override fun getNext() = datasource.getGesture()
}