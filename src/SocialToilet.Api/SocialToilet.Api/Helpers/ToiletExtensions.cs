namespace SocialToilet.Api.Helpers
{
    using System.Data.Entity.Spatial;

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
                           },
                           Description = toilet.Description
                       };
        }

        public static Toilet ToToilet(this ToiletViewModel viewModel)
        {
            return new Toilet()
            {
                Address = viewModel.Address,
                Description = viewModel.Description,
                Location = DbGeography.FromText(viewModel.Location.ToString())
            };
        }
    }
}