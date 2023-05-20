namespace SEP6.Models
{
    public class People
    {
        public int Id { get; set; }
        public string name { get; set; }
        public int birthYear { get; set; }
        public List<Directors> directors { get; set; }
        public List<Stars> stars { get; set; }
    }
}
