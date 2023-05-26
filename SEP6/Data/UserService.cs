using SEP6.Models;
using System.Net.Http.Headers;
using System.Xml.Linq;

namespace SEP6.Data
{
    public class UserService : IUserService
    {
       private HttpClient client;
       User user;

        public UserService(HttpClient client)
        {
            this.client = client;
 
        }

        public async Task<People> LoginUser(string name, int Id)
        {
            People people = new People();

            Console.WriteLine("Entering UserService");
            string baseUrl = "https://app-backend-sep-230516174355.azurewebsites.net/login";
            string username = name;
            int userId = Id;

            string encodedUsername = Uri.EscapeDataString(username);
            string url = $"{baseUrl}?username={encodedUsername}&userId={userId}";
            HttpRequestMessage httpRequestMessage = new HttpRequestMessage(HttpMethod.Post, url);
            
            


            var response = await client.SendAsync(httpRequestMessage);
            var responseStatusCode = response.StatusCode.ToString().ToLower();

            if (responseStatusCode.Equals("ok"))
            {
                people.name = name;
                people.Id = Id;
                return people;
            }
            else
            {
                throw new Exception(responseStatusCode);
            }
            
        }
        public async Task<User> PostFavoriteMovies(string username, int movieId)
        {
            User user = new User();

            Console.WriteLine("Entering UserService");
            string baseUrl = "https://app-backend-sep-230516174355.azurewebsites.net/login";
            string name = username;
            int id = movieId;

            string encodedUsername = Uri.EscapeDataString(username);
            string url = $"{baseUrl}?username={encodedUsername}&userId={id}";
            HttpRequestMessage httpRequestMessage = new HttpRequestMessage(HttpMethod.Post, url);




            var response = await client.SendAsync(httpRequestMessage);
            var responseStatusCode = response.StatusCode.ToString().ToLower();

            //if (responseStatusCode.Equals("ok"))
            //{
            //    people.name = name;
            //    people.Id = Id;
            //    return people;
            //}
            //else
            //{
            //    throw new Exception(responseStatusCode);
            //}
        }
    }
}
