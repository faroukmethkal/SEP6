using SEP6.Models;

namespace SEP6.Data
{
    public interface IUserService
    {
        Task<People> LoginUser(string name, int Id);
    }
}
