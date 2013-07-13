namespace SocialToilet.Api.Controllers
{
    using System;
    using System.Collections.Generic;
    using System.Data.Entity;
    using System.Data.Entity.Spatial;
    using System.Linq;
    using System.Threading.Tasks;
    using System.Web.Http;

    using SocialToilet.Api.Database;
    using SocialToilet.Api.Helpers;
    using SocialToilet.Api.Models;
    using SocialToilet.Api.ViewModels;

    public class ToiletsController : ApiController
    {
        private SocialToiletContext db;

        public ToiletsController()
        {
            this.db = new SocialToiletContext();
        }

        [HttpGet]
        public async Task<IEnumerable<ToiletViewModel>> Near(double lat, double @long, double radiusInMeters)
        {
            var geoLocation = DbGeography.FromText(new Location { Latitude = lat, Longitude = @long }.ToString());

            var nearbyToilets = await this.db.Toilets.Where(t => geoLocation.Distance(t.Location) < radiusInMeters).ToListAsync();

            return nearbyToilets.Select(t => t.ToViewModel());
        }

        public async Task<ToiletViewModel> Get(Guid id)
        {
            var toilet = await this.db.Toilets.FindAsync(id);
            return toilet.ToViewModel();
        }

        // POST api/values
        public async Task Post(ToiletViewModel value)
        {
            var toilet = value.ToToilet();

            this.db.Toilets.Add(toilet);

            await this.db.SaveChangesAsync();
        }

        protected override void Dispose(bool disposing)
        {
            this.db.Dispose();
            base.Dispose(disposing);
        }
    }
}