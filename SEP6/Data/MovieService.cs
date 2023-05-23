using Newtonsoft.Json;
using SEP6.Models;
using System.Collections;
using System.Runtime.CompilerServices;
using System.Xml.Linq;
using static System.Net.WebRequestMethods;

namespace SEP6.Data
{
    public class MovieService : IMovieService
    {
        private HttpClient httpClient;

        public MovieService(HttpClient httpClient) { 
            this.httpClient = httpClient;
        }

        public async Task<Movies> IndexMovie(string title)
        {
            Movies movies = new Movies();
            string baseUrl = "https://app-backend-sep-230516174355.azurewebsites.net/movies?title=";
            string encodedTitle = Uri.EscapeDataString(title);
            string url = $"{baseUrl}?title={encodedTitle}";
            HttpRequestMessage httpRequestMessage = new HttpRequestMessage(HttpMethod.Get, url);


            var response = await httpClient.SendAsync(httpRequestMessage);
            var responseStatusCode = response.StatusCode.ToString().ToLower();

            if (responseStatusCode.Equals("ok"))
            {
                movies.title = title;
                return movies;
            }
            else
            {
                throw new Exception(responseStatusCode);
            }

        }

        public async Task<OMDBResult> GetPosterForMovie(string title)
        {
            string baseUrl = "http://www.omdbapi.com/";
            string apiKey = "c04f487";

            string encodedTitle = Uri.EscapeDataString(title);
            string url = $"{baseUrl}?s={encodedTitle}&apikey={apiKey}";

            var response = await httpClient.GetAsync(url);
            response.EnsureSuccessStatusCode();


            var result = await response.Content.ReadAsStringAsync();

            Console.WriteLine(result);
            OMDBResult moviesOMDBs = JsonConvert.DeserializeObject<OMDBResult>(result);
            Console.WriteLine("deserialized" + moviesOMDBs.Search[0].Title);
            return moviesOMDBs;
        }
    }
}
