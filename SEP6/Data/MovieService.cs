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
        

        public MovieService(HttpClient httpClient)
        {
            this.httpClient = httpClient;
        }

        public async Task<List<Movies>> IndexMovie(string title)
        {

            string baseUrl = "https://app-backend-sep-230516174355.azurewebsites.net/movies";
            string encodedTitle = Uri.EscapeDataString(title);
            string url = $"{baseUrl}?title={encodedTitle}";
           
            var response = await httpClient.GetAsync(url);
            response.EnsureSuccessStatusCode();

            var responseContent = await response.Content.ReadAsStringAsync();


            List<Movies> moviesResult = JsonConvert.DeserializeObject<List<Movies>>(responseContent);
            return moviesResult;

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


            OMDBResult moviesOMDBs = JsonConvert.DeserializeObject<OMDBResult>(result);

            return moviesOMDBs;
        }
    }
}
