namespace SocialToilet.Api.Migrations
{
    using System.Data.Entity.Migrations;
    using System.Data.Entity.Spatial;
    using System.Linq;

    using SocialToilet.Api.Database;
    using SocialToilet.Api.Models;

    internal sealed class Configuration : DbMigrationsConfiguration<SocialToiletContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
            AutomaticMigrationDataLossAllowed = true;
        }

        protected override void Seed(SocialToiletContext context)
        {
            if (context.Users.Count() != 0)
            {
                return;
            }

            // close to Rivadavia Park
            var formosa = new Toilet
            {
                Address = "Formosa 252",
                Description = "Zona residencial centrica",
                Location = DbGeography.FromText("POINT(-58.43185901641846 -34.617934476251946)")
            };

            var viel = new Toilet
            {
                Address = "Viel 350",
                Description = "Parrilla La silla electrica",
                Location = DbGeography.FromText("POINT(-58.43277096748352 -34.61893219106914)")
            };

            var fiuba = new Toilet
            {
                Address = "Paseo Colon 850",
                Description = "De la facultad. No recomendado.",
                Location = DbGeography.FromText("POINT(-58.3673894405365 -34.618128722554786)")
            };

            context.Toilets.Add(formosa);
            context.Toilets.Add(viel);
            context.Toilets.Add(fiuba);

            AddMcDonalds(context);

            context.SaveChanges();

            var sebastian = new User { Name = "srodriguez", Password = "password" };
            var damian = new User { Name = "dschenkelman", Password = "password" };
            var matias = new User { Name = "mservetto", Password = "password" };

            context.Users.Add(sebastian);
            context.Users.Add(damian);
            context.Users.Add(matias);

            context.SaveChanges();

            context.Ratings.Add(new Rating { ToiletId = formosa.Id, UserId = damian.Id, Value = 5 });
            context.Ratings.Add(new Rating { ToiletId = viel.Id, UserId = sebastian.Id, Value = 2.5 });
            context.Ratings.Add(new Rating { ToiletId = fiuba.Id, UserId = matias.Id, Value = 1 });

            context.SaveChanges();

            context.Traits.Add(new Trait { Description = "Dejan pasar sin consumir" });
            context.Traits.Add(new Trait { Description = "Tiene agua" });
            context.Traits.Add(new Trait { Description = "Tiene papel" });
            context.Traits.Add(new Trait { Description = "Tiene jabon" });
            context.Traits.Add(new Trait { Description = "Tiene espejo" });
            context.Traits.Add(new Trait { Description = "Las puertas de las cabinas cierran" });
            context.Traits.Add(new Trait { Description = "Vende insumos de higiene femenina" });
            context.Traits.Add(new Trait { Description = "Vende preservativos" });
            context.Traits.Add(new Trait { Description = "Apto para discapacitados" });
            context.Traits.Add(new Trait { Description = "Posee cambiador de bebes" });

            context.SaveChanges();
        }

        private static void AddMcDonalds(SocialToiletContext context)
        {
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "25 de Mayo 398",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-65.20329 -26.82569)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "25 de Mayo 52",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.17938 -31.41612)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "ACC. SUDESTE ENTRE RAMALLO Y NICARAGUA",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.337864 -34.67863)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Aeropuerto Ministro Pistarini",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.543446 -34.81035)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "ARENALES 3360 L.2044\\/2045A",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.41038 -34.58724)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "ARENALES 3360 L.2101",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.41038 -34.58724)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Arieta 3025",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.561775 -34.67777)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Arieta 3474",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.556713 -34.68156)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. 25 de Mayo 1264",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.79603 -34.35442)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Acc. Este 3280 (Bo. Guaymallén)",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-68.73636 -32.941807)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Alvear 81",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.49957 -34.489067)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Angel Torcuato de Alvear 2956",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.62246 -34.483105)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Antártida Argentina 703",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.414696 -34.792767)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Brown 266",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-62.26541 -38.722122)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Bunge 682",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-56.8647 -37.113136)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Cabildo 2254",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.45791 -34.560207)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Cabildo 2523",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.46008 -34.558132)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Cabildo 742",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.444324 -34.569923)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Calchaquí 3950",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.27876 -34.759693)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Callao 131",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.392056 -34.607216)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Castex y Formosa",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.502953 -34.85537)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Centenario 3500",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.807003 -27.472572)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Centenario 72",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.5133 -34.473732)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Centenario y Calle 504",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.00675 -34.882515)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Centenario y Distribuidor",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-57.992756 -34.895683)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Colon esq. Avellaneda",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.19561 -31.409931)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Colón y Mitre",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-68.84568 -32.894)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Colón y Pasco",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.2579 -31.394455)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Constitución 3950",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-57.543396 -37.97049)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Constitución y Ruta 2",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-57.580616 -37.95193)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "AV. CORDOBA 1643",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.645725 -32.94507)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Cordoba 1765",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.392464 -34.59918)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Córdoba 2039",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.396416 -34.5997)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Córdoba 3831",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.420616 -34.59729)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "AV. CORDOBA Y CAFERATTA",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.67106 -32.940403)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Corrientes 1267",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.384674 -34.603523)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Corrientes 1650",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.39016 -34.604332)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Corrientes 2558",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.402935 -34.60437)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Corrientes 3247",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.410957 -34.60363)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Corrientes 3247 (Patio de Comidas)",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.410957 -34.60363)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Corrientes 3247 (PB)",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.410957 -34.60363)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Corrientes 3966",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.42051 -34.60325)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Corrientes 5230",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.439156 -34.5993)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Corrientes 980",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.380653 -34.60368)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. de los Constituyentes 6020",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.50561 -34.572033)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. de Mayo 650",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.375618 -34.607746)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. del Libertador 14701\\/19",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.496525 -34.477703)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. del Libertador 2194",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.54619 -34.446407)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. del Libertador 2803",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.481052 -34.50424)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. del Libertador 7112",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.45661 -34.547295)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Elcano 3240",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.45966 -34.57363)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Entre Ríos y Av. Caseros",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.390385 -34.634113)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Espora 611",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.38886 -34.795998)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Fernández de la Cruz 4602",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.46069 -34.67474)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Francisco Beiró 5275",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.524708 -34.613728)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Fuerza Aérea (Ruta 20) y Zapaleri",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.22913 -31.431475)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Gral. Lib. San Martin 1826 Oeste",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(0 0)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Hipólito Yrigoyen 10699",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.406628 -34.781593)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Hipólito Yrigoyen 7545",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.399033 -34.743217)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Hipólito Yrigoyen 8230",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.400715 -34.751804)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Humberto Primo y Moreno",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.25647 -34.724895)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Juan B. Alberdi 7450",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.51649 -34.661404)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Juan Domingo Perón 983",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.705082 -34.547382)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. La Plata 1798",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.424477 -34.63589)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Las Heras 3215",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.40624 -34.582962)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Libertador 756",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.727215 -34.665936)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Luis María Campos 900",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.437355 -34.568367)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Maipú 1050",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.48132 -34.52833)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Maipú 1050",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.48131 -34.5283)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Martín García 270",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.369183 -34.62804)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Melian y Av. Gral. Paz",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.487522 -34.545086)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Mitre 639",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.366844 -34.661057)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Mitre y Av. Brandsen",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.251038 -34.725407)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Monroe y Montañeses",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.452137 -34.553665)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. O'Higgings y Calmayo",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.168976 -31.454348)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Peralta Ramos 5499",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-57.59748 -38.04072)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Presidente Peron  (Av. 14) 4985",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.1886 -34.840137)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Pueyrredon 230",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.40611 -34.607002)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rafael Nunez 3987 Cerro",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.229454 -31.372921)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rafael Nunez 4764 Cerro",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.23644 -31.363998)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Ricardo Balbín 2549",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.547443 -34.57634)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rivadavia 14350",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.569523 -34.641445)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rivadavia 16335",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.593727 -34.646122)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rivadavia 2199",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.39757 -34.60955)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rivadavia 3145",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.411022 -34.610077)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rivadavia 3855",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.420464 -34.61085)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rivadavia 4515",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.42924 -34.61472)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rivadavia 5108",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.437847 -34.618465)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rivadavia 5730",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.446194 -34.622208)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rivadavia 6620",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.459103 -34.627644)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rivadavia 7302",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.468433 -34.63039)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Roca 3440",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-65.22624 -26.839619)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Rolon y Colectora Panamericana a Cap.",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.555286 -34.49759)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Sáenz 943",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.416424 -34.6513)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Salguero 3172",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.40404 -34.575603)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. San Juan 2826",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.403965 -34.62405)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. San Martín 2880",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.465816 -34.60264)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Santa Fe 1430",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.387432 -34.595722)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Santa Fe 1950",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.395275 -34.59575)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Santa Fe 2468",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.401917 -34.594513)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Santa Fe 4280",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.422386 -34.58066)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Santa Fe 4601",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.425274 -34.578648)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Scalabrini Ortiz 2034",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.42111 -34.587837)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Sucre y Av. Tomkinson",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.55538 -34.481266)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Tratado del Pilar y Panamericana Ramal Pilar",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.906067 -34.450264)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Triunvirato 4680",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.486168 -34.574127)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Urquiza 4785",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.5636 -34.605846)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Velez Sarsfield y Blvd. San Juan",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.18911 -31.420113)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Victorica y Acc. Oeste",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.791378 -34.634266)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Virrey Toledo 702",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-65.40336 -24.781475)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av. Warnes 2702",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.47663 -34.597084)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av.H.Yrigoyen 4475",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.39184 -34.70647)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Av.Vergara y Acc. Oeste",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.62953 -34.633648)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Bancalari s\\/n (e\\/Ruta 202 y Uruguay)",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.594902 -34.48615)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "BDO. De Irigoyen 2647",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.59421 -34.488255)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Belgrano 149",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.566326 -34.641983)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Bme. Mitre e Italia",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.57993 -34.422195)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Bv. Ballester y Alvear",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.552185 -34.548187)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Bv. Buenos Aires 150",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.46398 -34.818394)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Bv. Oroño y Av. Battle",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.664494 -33.009205)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Bv. San Martín 3099",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.56239 -34.61117)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "BVD. ORONO Y AV. CURA",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.660282 -32.971157)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Calle 47 Número 631",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-57.952766 -34.9137)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Calle 8 esq. 50",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-57.950783 -34.915848)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Capitán Bermdez y White",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.52312 -34.514637)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Cno. de Cintura y Ruta 3",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.560482 -34.68556)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Con. de Cintura y Av. Crovara",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.5416 -34.69985)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Córdoba 1001",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.636776 -32.946705)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Córdoba y Rouillon",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.70263 -32.934895)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Cuenca 3224",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.496117 -34.60035)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Diagonal Pueyrredón 3050",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-57.55228 -37.99886)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Duarte Quiros 1400 L.203",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.204384 -31.413181)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Florida 281",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.375168 -34.604782)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Florida 570",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.375336 -34.601418)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Florida 935",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.375443 -34.596996)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Fray Luis Beltran y Cardenosa",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.217606 -31.365564)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "General Paz 134",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.18625 -31.413593)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Granaderos 200 Local 1078",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.3488 -33.11649)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Güemes 2853",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-57.541355 -38.01526)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Guemes 897 L403",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.38724 -34.692375)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "H. Yrigoyen 13200",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.40036 -34.810757)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Jauretche 978",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.63135 -34.5885)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "José P. Varela 4850",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.517902 -34.61118)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Jose. A. de Goyechea 2851",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.21423 -31.379791)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "JUNIN THEDY INT.LEMAS Y ECHEVERRIA",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.67083 -32.926556)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Larrea 1573",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.397945 -34.588844)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Las Heras 301",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-68.8421 -32.885597)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Lavalle 964 o Carlos Pellegrini al 400",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.380512 -34.602497)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Lima 667",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.381607 -34.616096)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Mauricio Yadarola 1699 Barrio Talleres CDB",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.160965 -31.391092)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "NANSEN 323 - LOCAL 2141",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.68345 -32.90912)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Nazarre 3145",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.494186 -34.601913)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Panamericana 2650 (Bo. Godoy Cruz)",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-68.855225 -32.951298)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Panamericana Ramal Tigre y Ruta 202",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.57516 -34.452927)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Paraná 3745",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.52183 -34.50876)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Paraná 3745  (Patio de Comidas)",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.52183 -34.50876)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Paraná 3745 (1er. Piso)",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.52183 -34.50876)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Peatonal Belgrano 1645",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.53378 -34.574814)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Peatonal Laprida 177",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.39694 -34.76114)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Peatonal Rivadavia 124",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.258675 -34.7229)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Peatonal San Martín 1020\\/España 1\\/21",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.53051 -31.732803)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Peatonal San Martín 2139",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(0 0)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Perticone 1255",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-68.0416 -38.959976)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Perticone 545 (Acc.Multitrocha)",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-68.051445 -38.959885)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Pertincone 546",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-68.051796 -38.959892)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Pje. M. Giuffra 242 (y Paseo Colón)",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.3698 -34.617764)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Posadas 1227",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.383915 -34.589355)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "PUERTO SANTA FE- DIQUE 1- SHOPPING LA RIBERA",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.702362 -31.647446)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Pza. España",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-64.18501 -31.428553)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Ramón Falcón 7115",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.529266 -34.640556)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Ruta 2 Km. 115",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-57.97977 -35.573112)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Ruta 21 e Ing. Luque",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.584747 -34.750526)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Ruta 26 y Panamericana Ramal Pilar",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.782784 -34.44079)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Ruta 27 y Nordelta",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.64796 -34.398342)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Ruta 8 Km.50",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.90497 -34.449413)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Ruta 8 y Ruta 202",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.701855 -34.532368)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Ruta 9 Km. 72.5 ",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.94496 -34.191)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "San Lorenzo 3773",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.542442 -34.574158)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "San Martín 1177",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-68.83914 -32.88975)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "San Martín 1202 (Bo.Godoy Cruz)",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-68.84515 -32.912018)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "San Martín 191",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-59.11946 -34.56442)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "San Martín 2535",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-57.546066 -37.999874)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "San Martín 2702",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-60.70618 -31.644466)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Santa Rosa 1535",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.65674 -34.643032)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "SARMIENTO 2600 SHOP. PLAZA BAHIA BLANCA",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-62.24505 -38.699436)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Uruguay 790",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.386833 -34.59946)")
                    });
            context.Toilets.Add(
                new Toilet
                    {
                        Address = "Vicente López 2008",
                        Description = "Mc Donalds",
                        Location = DbGeography.FromText("POINT(-58.393066 -34.58917)")
                    });
        }
    }
}
