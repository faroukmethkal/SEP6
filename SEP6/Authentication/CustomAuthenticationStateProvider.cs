using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Components.Server;
using Microsoft.JSInterop;
using Newtonsoft.Json;
using SEP6.Data;
using SEP6.Models;
using System.Security.Claims;
using System.Threading.Tasks;

namespace SEP6.Authentication
{
    public class CustomAuthenticationStateProvider : ServerAuthenticationStateProvider
    {
        private readonly IJSRuntime jsonRuntime;
        private readonly IUserService serverData;

        public CustomAuthenticationStateProvider(IJSRuntime jsonRuntime, IUserService serverData)
        {
            this.jsonRuntime = jsonRuntime;
            this.serverData = serverData;
        }

        public override async Task<AuthenticationState> GetAuthenticationStateAsync()
        {
            var authState = await base.GetAuthenticationStateAsync();
            var user = authState.User;

            if (user?.Identity != null && user.Identity.IsAuthenticated)
            {
                return authState;
            }

            var identity = new ClaimsIdentity();
            string userAsJson = await jsonRuntime.InvokeAsync<string>("sessionStorage.getItem", "currentUser");
            if (!string.IsNullOrEmpty(userAsJson))
            {
                var cachedUser = JsonConvert.DeserializeObject<User>(userAsJson);
                var claims = new[]
                {
                    new Claim(ClaimTypes.Name, cachedUser.Name),  
                };
                identity = new ClaimsIdentity(claims, "custom");
            }

            return new AuthenticationState(new ClaimsPrincipal(identity));
        }

        public async Task ValidateLogin(string name, string password)
        {
            if (string.IsNullOrEmpty(name))
            {
                throw new Exception("Enter name");
            }
            else if (string.IsNullOrEmpty(password))
            {
                throw new Exception("Enter password");
            }

            ClaimsIdentity identity = new ClaimsIdentity("custom");
            try
            {
                User user = await serverData.LoginUser(name, password);

                string serializedData = JsonConvert.SerializeObject(user);
                await jsonRuntime.InvokeVoidAsync("sessionStorage.setItem", "currentUser", serializedData);
                identity = new ClaimsIdentity(new[]
                {
                    new Claim(ClaimTypes.Name, user.Name),
                }, "custom");
            }
            catch (Exception e)
            {
                throw new Exception(e.Message);
            }

            var authState = new AuthenticationState(new ClaimsPrincipal(identity));
            NotifyAuthenticationStateChanged(Task.FromResult(authState));
            SetAuthenticationState(Task.FromResult(authState));
        }

        public void Logout()
        {
            jsonRuntime.InvokeVoidAsync("sessionStorage.removeItem", "currentUser");
            var authState = new AuthenticationState(new ClaimsPrincipal(new ClaimsIdentity()));
            NotifyAuthenticationStateChanged(Task.FromResult(authState));
            SetAuthenticationState(Task.FromResult(authState));
        }
    }
}