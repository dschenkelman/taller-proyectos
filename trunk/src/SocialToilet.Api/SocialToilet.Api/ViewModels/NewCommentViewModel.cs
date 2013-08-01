namespace SocialToilet.Api.ViewModels
{
    using System;

    public class NewCommentViewModel
    {
        public string Content { get; set; }

        public Guid UserId { get; set; }
    }
}