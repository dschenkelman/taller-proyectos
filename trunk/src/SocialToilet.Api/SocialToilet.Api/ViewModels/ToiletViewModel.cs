namespace SocialToilet.Api.ViewModels
{
    using System;

    using SocialToilet.Api.Models;

    public class ToiletViewModel
    {
        public Guid Id { get; set; }

        public Location Location { get; set; }

        public string Address { get; set; }
    }
}