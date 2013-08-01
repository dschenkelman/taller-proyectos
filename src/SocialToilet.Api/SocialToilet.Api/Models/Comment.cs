namespace SocialToilet.Api.Models
{
    using System;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;

    public class Comment
    {
        [Key, ForeignKey("Toilet"), Column(Order = 0)]
        public Guid ToiletId { get; set; }

        [Key, Column(Order = 1), DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int Id { get; set; }

        public virtual Toilet Toilet { get; set; }

        [ForeignKey("User")]
        public Guid UserId { get; set; }

        public virtual User User { get; set; }

        [MaxLength(140)]
        public string Content { get; set; }

        public DateTimeOffset PostedOn { get; set; }
    }
}