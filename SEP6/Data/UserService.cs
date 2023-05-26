using SEP6.Models;
using System.Net.Http.Headers;
using System.Xml.Linq;
using Newtonsoft.Json;
using System.Text;

namespace SEP6.Data
{
    public class UserService : IUserService
    {
       private HttpClient client;
       public User user { get; set; }

        public UserService(HttpClient client)
        {
            this.client = client;
 
        }

        public async Task<User> LoginUser(string name, string password)
        {
            User user = new User();

            Console.WriteLine("Entering LoginUser");
            string baseUrl = "https://app-backend-sep-230516174355.azurewebsites.net/login";
            string username = name;
            string userPassword = password;

            string encodedUsername = Uri.EscapeDataString(username);
            string url = $"{baseUrl}?username={encodedUsername}&password={userPassword}";
            HttpRequestMessage httpRequestMessage = new HttpRequestMessage(HttpMethod.Post, url);
            
            


            var response = await client.SendAsync(httpRequestMessage);
            var responseStatusCode = response.StatusCode.ToString().ToLower();

            if (responseStatusCode.Equals("ok"))
            {
                user.Name = name;
                user.Password = password;
                return user;
            }
            else
            {
                throw new Exception(responseStatusCode);
            }
            
        }
        public async Task<User> RegisterUser(string name, string userPassword)
        {
            User user = new User();

            Console.WriteLine("Entering RegisterUser");
            string baseUrl = "https://app-backend-sep-230516174355.azurewebsites.net/register";
            

            // Create a JSON object with the user's name and password
            var requestBody = new { username = name, password = userPassword };
            var jsonRequest = JsonConvert.SerializeObject(requestBody);
            var content = new StringContent(jsonRequest, Encoding.UTF8, "application/json");

            using (HttpClient client = new HttpClient())
            {
                // Send a POST request to the register endpoint
                var response = await client.PostAsync(baseUrl, content);
                var responseStatusCode = response.StatusCode.ToString().ToLower();

                if (response.IsSuccessStatusCode)
                {
                    user.Name = name;
                    user.Password = userPassword;
                    return user;
                }
                else
                {
                    throw new Exception(responseStatusCode);
                }
            }
        }

        public async Task<User> PostFavoriteMovies(string username, int movieId)
        {
            User user = new User();
            Movies movie = new Movies();

            Console.WriteLine("Entering PostFavoriteMovies");
            string baseUrl = "https://app-backend-sep-230516174355.azurewebsites.net/topList";
            string name = username;

            string encodedUsername = Uri.EscapeDataString(username);
            string url = $"{baseUrl}?username={encodedUsername}&movieId={movieId}";
            HttpRequestMessage httpRequestMessage = new HttpRequestMessage(HttpMethod.Post, url);

            using (HttpClient client = new HttpClient())
            {
                var response = await client.SendAsync(httpRequestMessage);
                var responseStatusCode = response.StatusCode.ToString().ToLower();

                if (responseStatusCode.Equals("ok"))
                {
                    user.Name = name;
                    movie.Id = movieId;

                    // Add the movie to the user's favorite movies list
                    user.Favorites.Add(movie);

                    return user;
                }
                else
                {
                    throw new Exception(responseStatusCode);
                }
            }
        }
    }
}
