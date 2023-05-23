using SEP6.Models;

namespace SEP6.Data
{
    public interface IMovieService
    {

        Task<List<Movies>> IndexMovie(string title);
        Task<OMDBResult> GetPosterForMovie(string title);
    }
}
