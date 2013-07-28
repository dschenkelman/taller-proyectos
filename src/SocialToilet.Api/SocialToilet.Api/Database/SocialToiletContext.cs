namespace SocialToilet.Api.Database
{
    using System.Data.Entity;

    using SocialToilet.Api.Migrations;
    using SocialToilet.Api.Models;

    public partial class SocialToiletContext : DbContext
    {
        public SocialToiletContext() : base("DefaultConnection")
        {
        }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            Database.SetInitializer(new MigrateDatabaseToLatestVersion<SocialToiletContext, Configuration>());
        }

        public DbSet<Toilet> Toilets { get; set; }

        public DbSet<Picture> Pictures { get; set; }

        public DbSet<User> Users { get; set; }

        public DbSet<Rating> Ratings { get; set; }
    }
}