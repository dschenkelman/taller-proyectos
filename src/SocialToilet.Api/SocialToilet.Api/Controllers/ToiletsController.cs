namespace SocialToilet.Api.Controllers
{
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
            var geoLocation = DbGeography.FromText(new Location() {Latitude = lat, Longitude = @long}.ToString());

            var nearbyToilets = await this.db.Toilets.Where(t => geoLocation.Distance(t.Location) < radiusInMeters).ToListAsync();

            return nearbyToilets.Select(t => t.ToViewModel());
        }

        protected override void Dispose(bool disposing)
        {
            this.db.Dispose();
            base.Dispose(disposing);
        }

        //// GET api/values/5
        //public string Get(int id)
        //{
        //    return "value";
        //}

        //// POST api/values
        //public void Post([FromBody]string value)
        //{
        //}

        //// PUT api/values/5
        //public void Put(int id, [FromBody]string value)
        //{
        //}

        //// DELETE api/values/5
        //public void Delete(int id)
        //{
        //}
    }
}