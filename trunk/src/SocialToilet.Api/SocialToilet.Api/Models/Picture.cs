namespace SocialToilet.Api.Models
{
    using System;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    public class Picture
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public Guid Id { get; set; }

        public byte[] Content { get; set; }

        public virtual Toilet Toilet { get; set; }

        [ForeignKey("Toilet")]
        public Guid ToiletId { get; set; }
    }
}