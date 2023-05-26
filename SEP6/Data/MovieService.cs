using Newtonsoft.Json;
using SEP6.Models;
using Syncfusion.Blazor.Inputs;
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

        public async Task<List<Movies>> IndexMovie(string searchedMovie)
        {

            string baseUrl = "https://app-backend-sep-230516174355.azurewebsites.net/movies";
            string encodedTitle = Uri.EscapeDataString(searchedMovie);
            string url = $"{baseUrl}?title={encodedTitle}";
            var response = await httpClient.GetAsync(url);
            response.EnsureSuccessStatusCode();
            var responseContent = await response.Content.ReadAsStringAsync();


            List<Movies> moviesResult = JsonConvert.DeserializeObject<List<Movies>>(responseContent);
            //if (moviesResult != null)
            //{
            //    foreach (var movie in moviesResult.Where(x => !String.IsNullOrEmpty(x.title)))
            //    {
            //        Console.WriteLine($"Processing movie: {movie.title}");
            //        var omdbResult = await GetMoviesFromOMDb(movie.title);
            //        var omdbMovieMatch = omdbResult.Search
            //            .Where(x => int.Parse(x.ImdbID.Replace("t", "")) == movie.Id)
            //            .FirstOrDefault();

            //        if (omdbMovieMatch != null)
            //        {
            //            movie.Type = omdbMovieMatch.Type;
            //            movie.Poster = omdbMovieMatch.Poster;
            //        }
            //    }
            //}

            //return moviesResult;

            if (moviesResult != null)
            {
                foreach (var movie in moviesResult.Where(x => !String.IsNullOrEmpty(x.title)))
                {
                    Console.WriteLine($"Processing movie: {movie.title}");
                    var omdbResult = await GetMoviesFromOMDb(movie.title);

                    if (omdbResult != null && omdbResult.Search != null)
                    {
                        var omdbMovieMatch = omdbResult.Search
                            .Where(x => int.Parse(x.ImdbID.Replace("t", "")) == movie.Id)
                            .FirstOrDefault();

                        if (omdbMovieMatch != null)
                        {
                            movie.Type = omdbMovieMatch.Type;
                            movie.Poster = omdbMovieMatch.Poster;
                        }
                    }
                    else
                    {
                        Console.WriteLine($"No OMDB result or search results for movie: {movie.title}");

                        movie.Type = "Unknown";
                        movie.Poster = "default-poster.jpg";
                    }
                }
            }

            return moviesResult;

        }


        public async Task<OMDBResult> GetMoviesFromOMDb(string title)
        {
            string baseUrl = "http://www.omdbapi.com/";
            string apiKey = "c04f487";

            string encodedTitle = Uri.EscapeDataString(title);
            string url = $"{baseUrl}?s={encodedTitle}&apikey={apiKey}";

            var response = await httpClient.GetAsync(url);
            response.EnsureSuccessStatusCode();


            var result = await response.Content.ReadAsStringAsync();
            var listOfMovieResults = JsonConvert.DeserializeObject<OMDBResult>(result);

            return listOfMovieResults;
        }


        //public async Task<OMDBResult> GetMoviesFromOMDb(string title)
        //{
        //    string baseUrl = "http://www.omdbapi.com/";
        //    string apiKey = "c04f487";

        //    string encodedTitle = Uri.EscapeDataString(title);
        //    string url = $"{baseUrl}?s={encodedTitle}&apikey={apiKey}";

        //    var response = await httpClient.GetAsync(url);
        //    response.EnsureSuccessStatusCode();


        //    var result = await response.Content.ReadAsStringAsync();
        //    var listOfMovieResults = JsonConvert.DeserializeObject<OMDBResult>(result);

        //    return listOfMovieResults.Search.Where(x => x.Title.Equals(title)).FirstOrDefault();
        //}

        //public async Task<List<MoviesOMDB>> GetOMDbPosters(string imdbId)
        //{
        //    var apiUrl = $"http://www.omdbapi.com/?i={imdbId}&apikey=c04f487";

        //    // Make the HTTP GET request
        //    var response = await httpClient.GetAsync(apiUrl);

        //    // Check if the request was successful
        //    if (response.IsSuccessStatusCode)
        //    {
        //        var content = await response.Content.ReadAsStringAsync();
        //        List<MoviesOMDB> = JsonConvert.DeserializeObject<List<MoviesOMDB>>(content);

        //        return omdbResult;
        //    }

        //    // Handle the case when the request was not successful
        //    // For example, you could throw an exception or return null
        //    return null;
        //}
    }
}
