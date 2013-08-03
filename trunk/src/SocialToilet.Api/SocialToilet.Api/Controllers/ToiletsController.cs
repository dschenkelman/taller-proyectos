namespace SocialToilet.Api.Controllers
{
    using System;
    using System.Collections.Generic;
    using System.Data.Entity;
    using System.Data.Entity.Spatial;
    using System.Linq;
    using System.Net;
    using System.Net.Http;
    using System.Threading.Tasks;
    using System.Web.Http;

    using SocialToilet.Api.Helpers;
    using SocialToilet.Api.Models;
    using SocialToilet.Api.ViewModels;

    [Authorize]
    public class ToiletsController : BaseController
    {
        [HttpGet]
        public async Task<IEnumerable<ToiletViewModel>> Near(double lat, double @long, double radiusInMeters)
        {
            var geoLocation = DbGeography.FromText(new Location { Latitude = lat, Longitude = @long }.ToString());

            System.Diagnostics.Debug.WriteLine(geoLocation.Latitude);

            var nearbyToilets = await this.db.Toilets.Where(t => geoLocation.Distance(t.Location) < radiusInMeters).ToListAsync();

            return nearbyToilets.Select(t => t.ToViewModel());
        }

        public async Task<ToiletViewModel> Get(Guid id)
        {
            var toilet = await this.db.Toilets.FindAsync(id);
            return toilet.ToViewModel();
        }

        public async Task<IEnumerable<ToiletViewModel>> Get()
        {
            return (await this.db.Toilets.ToListAsync()).Select(t => t.ToViewModel());
        }

        public async Task<HttpResponseMessage> Post(ToiletViewModel value)
        {
            var toilet = value.ToToilet();

            this.db.Toilets.Add(toilet);

            await this.db.SaveChangesAsync();

            var message = Request.CreateResponse(HttpStatusCode.Created, toilet.Id);

            return message;
        }
    }
}

