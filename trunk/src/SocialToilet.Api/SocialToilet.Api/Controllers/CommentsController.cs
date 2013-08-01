namespace SocialToilet.Api.Controllers
{
    using System;
    using System.Collections.Generic;
    using System.Data.Entity;
    using System.Linq;
    using System.Net;
    using System.Net.Http;
    using System.Threading.Tasks;

    using SocialToilet.Api.Helpers;
    using SocialToilet.Api.Models;
    using SocialToilet.Api.ViewModels;

    public class CommentsController : BaseController
    {
        public async Task<IEnumerable<UserCommentViewModel>> Get(Guid toiletId)
        {
            var comments = await this.db.Comments.Where(c => c.ToiletId == toiletId).ToListAsync();

            return comments.Select(c => c.ToViewModelWithUserName());
        }

        public async Task<HttpResponseMessage> Post(Guid toiletId, NewCommentViewModel viewModel)
        {
            var comment = new Comment
                              {
                                  Content = viewModel.Content, 
                                  ToiletId = toiletId, 
                                  UserId = viewModel.UserId,
                                  PostedOn = DateTimeOffset.Now
                              };

            this.db.Comments.Add(comment);

            await this.db.SaveChangesAsync();

            var message = Request.CreateResponse(HttpStatusCode.Created);

            return message;
        }
    }
}