namespace SocialToilet.Api.Helpers
{
    using SocialToilet.Api.Models;
    using SocialToilet.Api.ViewModels;

    public static class ToiletExtensions
    {
        public static ToiletViewModel ToViewModel(this Toilet toilet)
        {
            return new ToiletViewModel()
                       {
                           Address = toilet.Address,
                           Id = toilet.Id,
                           Location = new Location 
                           { 
                               Latitude = toilet.Location.Latitude ?? 0,
                               Longitude = toilet.Location.Longitude ?? 0
                           }
                       };
        }
    }
}