using SEP6.Models;

namespace SEP6.Data
{
    public interface IMovieService
    {

        Task<List<Movies>> IndexMovie(string title);
        Task<OMDBResult> GetMoviesFromOMDb(string title);
        //Task<MoviesOMDB> GetOMDbPosters(string imdbId);
    }
}
