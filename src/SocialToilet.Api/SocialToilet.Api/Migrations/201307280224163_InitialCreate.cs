namespace SocialToilet.Api.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class InitialCreate : DbMigration
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
                        UserName = c.String(nullable: false, maxLength: 128),
                        Value = c.Double(nullable: false),
                    })
                .PrimaryKey(t => new { t.ToiletId, t.UserName })
                .ForeignKey("dbo.Users", t => t.UserName, cascadeDelete: true)
                .ForeignKey("dbo.Toilets", t => t.ToiletId, cascadeDelete: true)
                .Index(t => t.UserName)
                .Index(t => t.ToiletId);
            
            CreateTable(
                "dbo.Users",
                c => new
                    {
                        Name = c.String(nullable: false, maxLength: 128),
                        Password = c.String(),
                    })
                .PrimaryKey(t => t.Name);
            
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
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Pictures", "ToiletId", "dbo.Toilets");
            DropForeignKey("dbo.Ratings", "ToiletId", "dbo.Toilets");
            DropForeignKey("dbo.Ratings", "UserName", "dbo.Users");
            DropIndex("dbo.Pictures", new[] { "ToiletId" });
            DropIndex("dbo.Ratings", new[] { "ToiletId" });
            DropIndex("dbo.Ratings", new[] { "UserName" });
            DropTable("dbo.Pictures");
            DropTable("dbo.Users");
            DropTable("dbo.Ratings");
            DropTable("dbo.Toilets");
        }
    }
}
