// Local Data Source Interface Implementation
class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
) : MovieLocalDataResource {
    override suspend fun getMovie(movieId: Int): MovieEntity? {
        return movieDao.getMovie(movieId)?.toData()
    }

    override suspend fun saveMovie(movie: MovieEntity) {
        movieDao.insert(movie.toLocal())
    }
}

// DAO
@Dao
interface MovieDao : BaseDao<MovieLocal> {
    @Query("SELECT * FROM ${RoomConstant.Table.MOVIE} WHERE id = :movieId")
    suspend fun getMovie(movieId: Int): MovieLocal?
}