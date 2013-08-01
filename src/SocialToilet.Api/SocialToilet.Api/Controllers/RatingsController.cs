namespace SocialToilet.Api.Controllers
{
    using System;
    using System.Data.Entity;
    using System.Linq;
    using System.Net;
    using System.Net.Http;
    using System.Threading.Tasks;
    using System.Web.Http;

    using SocialToilet.Api.Models;
    using SocialToilet.Api.ViewModels;

    [Authorize]
    public class RatingsController : BaseController
    {
        [HttpGet]
        public async Task<double> Average(Guid toiletId)
        {
            var rating = await this.db.Ratings
                .Where(r => r.ToiletId == toiletId)
                .AverageAsync(r => r.Value);
            return rating;
        }

        public async Task<HttpResponseMessage> Post(Guid toiletId, UserRatingViewModel ratingViewModel)
        {
            var rating = new Rating
                             {
                                 ToiletId = toiletId,
                                 UserId = ratingViewModel.UserId,
                                 Value = ratingViewModel.Rating
                             };

            this.db.Ratings.Add(rating);

            await this.db.SaveChangesAsync();
            var message = Request.CreateResponse(HttpStatusCode.Created);

            return message;
        }
    }
}