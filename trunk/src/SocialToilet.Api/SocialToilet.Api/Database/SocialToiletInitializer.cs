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
            context.Toilets.Add(new Toilet { Address ="Formosa 252", Location = DbGeography.FromText("POINT(-34.617934476251946 -58.43185901641846)") });
            context.Toilets.Add(new Toilet { Address = "Viel 350", Location = DbGeography.FromText("POINT(-34.61893219106914 -58.43277096748352)") });

            // close to FIUBA
            context.Toilets.Add(new Toilet { Address = "Paseo Colon 850", Location = DbGeography.FromText("POINT(-34.618128722554786 -58.3673894405365)") });

            context.SaveChanges();
        }
    }
}