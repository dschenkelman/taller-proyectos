namespace SocialToilet.Api.Models
{
    using System;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    public class Rating
    {
        [Key, Column(Order = 0)]
        public Guid ToiletId { get; set; }

        [Key, Column(Order = 1)]
        public Guid UserId { get; set; }

        [ForeignKey("UserId")]
        public virtual User User { get; set; }

        [ForeignKey("ToiletId")]
        public virtual Toilet Toilet { get; set; }

        [Required]
        public double Value { get; set; }
    }
}