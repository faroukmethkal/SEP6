using Blazored.LocalStorage;
using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Components.Web;
using SEP6;
using SEP6.Authentication;
using SEP6.Data;

var builder = WebApplication.CreateBuilder(args);

var startup = new Startup(builder.Configuration);
startup.ConfigureServices(builder.Services);


// Add services to the container.
builder.Services.AddRazorPages();
builder.Services.AddServerSideBlazor();
builder.Services.AddSingleton<WeatherForecastService>();
builder.Services.AddHttpContextAccessor();
builder.Services.AddScoped<IUserService, UserService>();
builder.Services.AddBlazoredLocalStorage(config => config.JsonSerializerOptions.WriteIndented = true);

builder.Services.AddScoped<AuthenticationStateProvider, CustomAuthenticationStateProvider>();
builder.Services.AddOptions();
builder.Services.AddAuthorizationCore();



// Register HttpClient service
builder.Services.AddScoped<HttpClient>();

var app = builder.Build();
app.UseAuthorization();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();

app.UseStaticFiles();

app.UseRouting();

app.MapBlazorHub();
app.MapFallbackToPage("/_Host");

app.Run();
