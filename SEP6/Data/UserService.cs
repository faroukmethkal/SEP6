using SEP6.Models;
using System.Net.Http.Headers;

namespace SEP6.Data
{
    public class UserService : IServiceUser
    {
       private HttpClient client;

        public UserService(HttpClient client)
        {
            this.client = client;
 
        }

        public async Task<People> LoginUser(string name, int Id)
        {
            People people = new People();


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
            throw new Exception(responseStatusCode);

        }
    }
}
