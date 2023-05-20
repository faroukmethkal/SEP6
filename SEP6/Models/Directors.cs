namespace SEP6.Models
{
    public class Directors
    {
        public int Id { get; set; }
        public int movieId { get; set; }
        public Movies movies { get; set; }
        public int peopleId { get; set; }
        public People people { get; set; }

    }
}
