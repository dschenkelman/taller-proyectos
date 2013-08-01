namespace SocialToilet.Api.Helpers
{
    using SocialToilet.Api.Models;
    using SocialToilet.Api.ViewModels;

    public static class CommentExtensions
    {
        public static UserCommentViewModel ToViewModelWithUserName(this Comment comment)
        {
            return new UserCommentViewModel
                        {
                           Content = comment.Content,
                           UserId = comment.UserId,
                           UserName = comment.User.Name,
                           PostedOn = comment.PostedOn
                        };
        }
    }
}