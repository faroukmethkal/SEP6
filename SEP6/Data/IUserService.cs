using SEP6.Models;

namespace SEP6.Data
{
    public interface IUserService
    {
        Task<User> LoginUser(string name, string password);
        Task<User> RegisterUser(string name, string userPassword);
        Task<User> PostFavoriteMovies(string username, int movieId);
    }
}
