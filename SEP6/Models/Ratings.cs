namespace SEP6.Models
{
    public class Ratings
    {
        public int Id { get; set; }
        public int movieId { get; set; }
        public double rating { get; set; }
        public int numberOfVotes { get; set; }
    }
}
