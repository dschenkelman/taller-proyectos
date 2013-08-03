namespace SocialToilet.Api.ViewModels
{
    using System;

    public class UserCommentViewModel
    {
        public string Content { get; set; }

        public string UserName { get; set; }

        public Guid UserId { get; set; }

        public string PostedOn { get; set; }
    }
}