namespace SocialToilet.Api.Controllers
{
    using System;
    using System.Collections.Generic;
    using System.Data.Entity;
    using System.Linq;
    using System.Net;
    using System.Net.Http;
    using System.Threading.Tasks;
    using System.Web.Http;

    using SocialToilet.Api.Models;

    [Authorize]
    public class PicturesController : BaseController
    {
        public async Task<IEnumerable<Guid>> Get(Guid toiletId)
        {
            return await this.db.Pictures.Select(p => p.Id).ToListAsync();
        }

        public async Task<byte[]> Get(Guid toiletId, Guid itemId)
        {
            var picture = await this.db.Pictures.FindAsync(itemId);

            return picture.Content;
        }

        public async Task<HttpResponseMessage> Post(Guid toiletId)
        {
            // Read the MIME multipart asynchronously content using the stream provider we just created.
            var imageBytes = await Request.Content.ReadAsByteArrayAsync();

            var picture = new Picture { Content = imageBytes, ToiletId = toiletId };

            this.db.Pictures.Add(picture);

            await this.db.SaveChangesAsync();

            var message = Request.CreateResponse(HttpStatusCode.Created, picture.Id);

            return message;
        }
    }
}
