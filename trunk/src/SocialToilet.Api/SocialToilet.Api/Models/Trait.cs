namespace SocialToilet.Api.Models
{
    using System.Collections.Generic;

    using Newtonsoft.Json;

    public class Trait
    {
        public int Id { get; set; }
        
        public string Description { get; set; }

        [JsonIgnore]
        public virtual ICollection<Toilet> Toilets { get; set; }
    }
}