namespace SocialToilet.Api.ViewModels
{
    using System;

    public class UserRatingViewModel
    {
        public Guid UserId { get; set; }

        public double Rating { get; set; }
    }
}