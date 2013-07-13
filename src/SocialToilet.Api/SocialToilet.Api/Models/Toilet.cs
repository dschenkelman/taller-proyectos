namespace SocialToilet.Api.Models
{
    using System;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public class Toilet
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid Id { get; set; }

        public DbGeography Location { get; set; }

        public string Address { get; set; }

        public string Description { get; set; }
    }
}