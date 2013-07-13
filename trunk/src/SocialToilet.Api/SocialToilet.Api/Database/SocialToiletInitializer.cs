namespace SocialToilet.Api.Database
{
    using System.Data.Entity;
    using System.Data.Entity.Spatial;

    using SocialToilet.Api.Models;

    public class SocialToiletInitializer : DropCreateDatabaseAlways<SocialToiletContext>
    {
        protected override void Seed(SocialToiletContext context)
        {
            // close to Rivadavia Park
            context.Toilets.Add(new Toilet { Address = "Formosa 252", Location = DbGeography.FromText("POINT(-58.43185901641846 -34.617934476251946)") });
            context.Toilets.Add(new Toilet { Address = "Viel 350", Location = DbGeography.FromText("POINT(-58.43277096748352 -34.61893219106914)") });

            // close to FIUBA
            context.Toilets.Add(new Toilet { Address = "Paseo Colon 850", Location = DbGeography.FromText("POINT(-58.3673894405365 -34.618128722554786)") });

            context.SaveChanges();
        }
    }
}