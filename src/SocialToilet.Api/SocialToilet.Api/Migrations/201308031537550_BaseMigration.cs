namespace SocialToilet.Api.Migrations
{
    using System.Data.Entity.Migrations;
    
    public partial class BaseMigration : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Toilets",
                c => new
                    {
                        Id = c.Guid(nullable: false, identity: true),
                        Location = c.Geography(),
                        Address = c.String(),
                        Description = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Ratings",
                c => new
                    {
                        ToiletId = c.Guid(nullable: false),
                        UserId = c.Guid(nullable: false),
                        Value = c.Double(nullable: false),
                    })
                .PrimaryKey(t => new { t.ToiletId, t.UserId })
                .ForeignKey("dbo.Users", t => t.UserId, cascadeDelete: true)
                .ForeignKey("dbo.Toilets", t => t.ToiletId, cascadeDelete: true)
                .Index(t => t.UserId)
                .Index(t => t.ToiletId);
            
            CreateTable(
                "dbo.Users",
                c => new
                    {
                        Id = c.Guid(nullable: false, identity: true),
                        Name = c.String(maxLength: 255),
                        Password = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Pictures",
                c => new
                    {
                        Id = c.Guid(nullable: false, identity: true),
                        Content = c.Binary(),
                        ToiletId = c.Guid(nullable: false),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Toilets", t => t.ToiletId, cascadeDelete: true)
                .Index(t => t.ToiletId);
            
            CreateTable(
                "dbo.Comments",
                c => new
                    {
                        ToiletId = c.Guid(nullable: false),
                        Id = c.Int(nullable: false, identity: true),
                        UserId = c.Guid(nullable: false),
                        Content = c.String(maxLength: 140),
                        PostedOn = c.DateTimeOffset(nullable: false),
                    })
                .PrimaryKey(t => new { t.ToiletId, t.Id })
                .ForeignKey("dbo.Toilets", t => t.ToiletId, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.UserId, cascadeDelete: true)
                .Index(t => t.ToiletId)
                .Index(t => t.UserId);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Comments", "UserId", "dbo.Users");
            DropForeignKey("dbo.Comments", "ToiletId", "dbo.Toilets");
            DropForeignKey("dbo.Pictures", "ToiletId", "dbo.Toilets");
            DropForeignKey("dbo.Ratings", "ToiletId", "dbo.Toilets");
            DropForeignKey("dbo.Ratings", "UserId", "dbo.Users");
            DropIndex("dbo.Comments", new[] { "UserId" });
            DropIndex("dbo.Comments", new[] { "ToiletId" });
            DropIndex("dbo.Pictures", new[] { "ToiletId" });
            DropIndex("dbo.Ratings", new[] { "ToiletId" });
            DropIndex("dbo.Ratings", new[] { "UserId" });
            DropTable("dbo.Comments");
            DropTable("dbo.Pictures");
            DropTable("dbo.Users");
            DropTable("dbo.Ratings");
            DropTable("dbo.Toilets");
        }
    }
}
