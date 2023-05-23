using SEP6.Models;

namespace SEP6.Data
{
    public interface IMovieService
    {

        Task<Movies> IndexMovie(string title);
        Task<OMDBResult> GetPosterForMovie(string title);
    }
}
