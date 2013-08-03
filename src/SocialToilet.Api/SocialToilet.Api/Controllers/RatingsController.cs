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
        public async Task<AverageRatingViewModel> Average(Guid toiletId)
        {
            var rating =
                await
                this.db.Ratings.Where(r => r.ToiletId == toiletId)
                    .GroupBy(r => r.ToiletId)
                    .Select(g => new AverageRatingViewModel { Rating = g.Average(r => r.Value), Votes = g.Count() })
                    .FirstOrDefaultAsync();

            return rating ?? new AverageRatingViewModel { Votes = 0 };
        }

        public async Task<HttpResponseMessage> Get(Guid toiletId, Guid userId)
        {
            var rating = this.db.Ratings.Find(toiletId, userId);

            if (rating == null)
            {
                return Request.CreateResponse(HttpStatusCode.NotFound);
            }

            return Request.CreateResponse(HttpStatusCode.OK, rating.Value);
        }

        public async Task<HttpResponseMessage> Put(Guid toiletId, UserRatingViewModel ratingViewModel)
        {
            var existingRating = this.db.Ratings.Find(toiletId, ratingViewModel.UserId);

            if (existingRating != null)
            {
                existingRating.Value = ratingViewModel.Rating;
                await this.db.SaveChangesAsync();
                return Request.CreateResponse(HttpStatusCode.OK);
            }

            return Request.CreateResponse(HttpStatusCode.NotFound);
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

            return Request.CreateResponse(HttpStatusCode.Created);
        }
    }
}