namespace SocialToilet.Api.Models
{
    using System.ComponentModel.DataAnnotations;

    public class User
    {
        [Key]
        public string Name { get; set; }

        public string Password { get; set; }
    }
}