namespace SocialToilet.Api.Controllers
{
    using System.Net;
    using System.Net.Http;
    using System.Threading.Tasks;
    using System.Web.Http;

    using SocialToilet.Api.Models;
    using SocialToilet.Api.ViewModels;

    [Authorize]
    public class UsersController : BaseController
    {
        public async Task<HttpResponseMessage> Post(UserInfoViewModel userInfo)
        {
            var user = new User { Name = userInfo.Name, Password = userInfo.Password };
            this.db.Users.Add(user);

            await this.db.SaveChangesAsync();

            return Request.CreateResponse(HttpStatusCode.Created, user.Id);
        }
    }
}