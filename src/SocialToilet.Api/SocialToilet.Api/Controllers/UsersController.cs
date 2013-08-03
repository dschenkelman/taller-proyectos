namespace SocialToilet.Api.Controllers
{
    using System;
    using System.Data.Entity;
    using System.Linq;
    using System.Net;
    using System.Net.Http;
    using System.Threading;
    using System.Threading.Tasks;
    using System.Web;
    using System.Web.Http;

    using SocialToilet.Api.Models;
    using SocialToilet.Api.ViewModels;

    public class UsersController : BaseController
    {
        [HttpPost]
        [Authorize]
        public async Task<HttpResponseMessage> Auth()
        {
            var userName = Thread.CurrentPrincipal.Identity.Name;

            var user = await this.db.Users.Where(u => u.Name == userName).FirstOrDefaultAsync();

            if (user == null)
            {
                return Request.CreateResponse(HttpStatusCode.Unauthorized);
            }

            return Request.CreateResponse(HttpStatusCode.OK, user.Id);
        }

        public async Task<HttpResponseMessage> Post(UserInfoViewModel userInfo)
        {
            if (string.IsNullOrEmpty(userInfo.Name) || string.IsNullOrEmpty(userInfo.Password))
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, new HttpError("One of the request inputs is not valid."));
            }

            var repeatedName = await this.db.Users.AnyAsync(u => u.Name.Equals(userInfo.Name, StringComparison.InvariantCultureIgnoreCase));

            if (repeatedName)
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, new HttpError("User name already exists."));
            }

            var user = new User { Name = userInfo.Name, Password = userInfo.Password };
            this.db.Users.Add(user);

            await this.db.SaveChangesAsync();

            return Request.CreateResponse(HttpStatusCode.Created, user.Id);
        }
    }
}