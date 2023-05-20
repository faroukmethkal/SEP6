using SEP6.Models;

namespace SEP6.Data
{
    public interface IServiceUser
    {
        Task<People> LoginUser(string name, int Id);
    }
}
