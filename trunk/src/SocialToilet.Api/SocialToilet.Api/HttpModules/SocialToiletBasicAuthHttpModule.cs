namespace SocialToilet.Api.HttpModules
{
    using System;

    using SocialToilet.Api.Database;

    public class SocialToiletBasicAuthHttpModule : BasicAuthHttpModule
    {
        private SocialToiletContext db;

        public SocialToiletBasicAuthHttpModule()
        {
            this.db = new SocialToiletContext();
        }

        protected override bool CheckPassword(string username, string password)
        {
            var user = this.db.Users.Find(username);

            if (user == null)
            {
                return false;
            }

            return user.Password.Equals(password, StringComparison.InvariantCulture);
        }

        public void Dispose()
        {
            this.db.Dispose();
        }
    }
}