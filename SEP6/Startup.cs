using Microsoft.AspNetCore.Components.Authorization;
using SEP6.Authentication;
using SEP6.Data;
using Blazored.LocalStorage;
using Blazored.Toast;

namespace SEP6
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddRazorPages();
            services.AddServerSideBlazor();

            services.AddScoped<CustomAuthenticationStateProvider>();
            services.AddScoped<AuthenticationStateProvider>(p =>
                            p.GetRequiredService<CustomAuthenticationStateProvider>());
            services.AddBlazoredLocalStorage(config => config.JsonSerializerOptions.WriteIndented = true);

            services.AddScoped<IUserService, UserService>();
            services.AddScoped<IMovieService, MovieService>();
            services.AddHttpContextAccessor();

            services.AddScoped<AuthenticationStateProvider, CustomAuthenticationStateProvider>();
            services.AddHttpClient<IUserService, UserService>(client =>
            {
                client.BaseAddress = new Uri("https://app-backend-sep-230516174355.azurewebsites.net/");
            });

            services.AddAuthorization(options =>
            {
                options.AddPolicy("MustBeAdmin", a =>
                {
                    a.RequireAuthenticatedUser().RequireClaim("Role", "admin");
                });
            });
        }
    }
}
