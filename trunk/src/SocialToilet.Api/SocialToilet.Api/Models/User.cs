namespace SocialToilet.Api.Models
{
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;

    public class User
    {
        [Key]
        public string Name { get; set; }

        public string Password { get; set; }

        public virtual ICollection<Rating> Ratings { get; set; }
    }
}