namespace SocialToilet.Api.Controllers
{
    using System;
    using System.Data.Entity;
    using System.Linq;
    using System.Threading.Tasks;
    using System.Web.Http;

    using SocialToilet.Api.Database;

    [Authorize]
    public class RatingsController : ApiController
    {
        private SocialToiletContext db;

        public RatingsController()
        {
            this.db = new SocialToiletContext();
        }

        public async Task<double> Get(Guid toiletId)
        {
            var rating = await this.db.Ratings.Where(r => r.ToiletId == toiletId).AverageAsync(r => r.Value);
            return rating;
        }

        protected override void Dispose(bool disposing)
        {
            this.db.Dispose();
            base.Dispose(disposing);
        }
    }
}