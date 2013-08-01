namespace SocialToilet.Api.Controllers
{
    using System.Web.Http;

    using SocialToilet.Api.Database;

    [Authorize]
    public class BaseController : ApiController
    {
        protected SocialToiletContext db;

        public BaseController()
        {
            this.db = new SocialToiletContext();
        }

        protected override void Dispose(bool disposing)
        {
            this.db.Dispose();
            base.Dispose(disposing);
        }
    }
}