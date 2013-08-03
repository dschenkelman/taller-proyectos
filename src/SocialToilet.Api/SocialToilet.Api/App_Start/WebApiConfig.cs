namespace SocialToilet.Api
{
    using System.Web.Http;

    using Newtonsoft.Json;
    using Newtonsoft.Json.Serialization;

    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            config.Routes.MapHttpRoute(
                name: "NestedUserAssets",
                routeTemplate: "api/users/{action}",
                defaults: new { controller = "users", action = RouteParameter.Optional });
            
            config.Routes.MapHttpRoute(
                name: "NestedToiletAssets",
                routeTemplate: "api/toilets/{toiletId}/{controller}/{itemId}",
                defaults: new { itemId = RouteParameter.Optional });

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional });

            var settings = new JsonSerializerSettings
                               {
                                   ContractResolver = new CamelCasePropertyNamesContractResolver()
                               };
            config.Formatters.JsonFormatter.SerializerSettings = settings;
        }
    }
}
