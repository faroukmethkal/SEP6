using SEP6.Models;

namespace SEP6.Data
{
    public interface IMovieService
    {

        Task<Movies> IndexMovie(string title);
        Task<Movies> GetPosterForMovie(string title);
    }
}
