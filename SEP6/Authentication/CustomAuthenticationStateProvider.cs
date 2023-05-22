using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Components.Server;
using Microsoft.JSInterop;
using Newtonsoft.Json;
using SEP6.Data;
using SEP6.Models;
using System.Security.Claims;

namespace SEP6.Authentication
{
    public class CustomAuthenticationStateProvider : AuthenticationStateProvider
    {
        private readonly IJSRuntime jsonRuntime;
        private readonly IUserService serverData;
        private People cachedUser;

        public CustomAuthenticationStateProvider(IJSRuntime jsonRuntime, IUserService serverData)
        {
            this.jsonRuntime = jsonRuntime;
            this.serverData = serverData;
        }

     public override async Task<AuthenticationState> GetAuthenticationStateAsync()
        {
            var identity = new ClaimsIdentity();
            if (cachedUser == null)
            {
                string userAsJson = await jsonRuntime.InvokeAsync<string>("sessionStorage.getItem", "currentUser");
                if (!string.IsNullOrEmpty(userAsJson))
                {
                    People tmp = JsonConvert.DeserializeObject<People>(userAsJson);
                    await ValidateLogin(tmp.name, tmp.Id);
                }
            }

            ClaimsPrincipal cachedClaimsPrincipal = new ClaimsPrincipal(identity);
            return await Task.FromResult(new AuthenticationState(cachedClaimsPrincipal));
        }
 
        public async Task ValidateLogin(string name, int Id)
        {
            if (string.IsNullOrEmpty(name))
            {
                throw new Exception("Enter name");
            }
            else if (Id == null)
            {
                throw new Exception("Enter Id");
            } 
            

            ClaimsIdentity identity = new ClaimsIdentity("authorized");
            try
            {
                People user1 = await serverData.LoginUser(name, Id);

                string serialisedData = JsonConvert.SerializeObject(user1);
                await jsonRuntime.InvokeVoidAsync("sessionStorage.setItem", "currentUser", serialisedData);
                cachedUser = user1;
            }
            catch (Exception e)
            {
                throw new Exception(e.Message);
            }

            NotifyAuthenticationStateChanged(
                Task.FromResult(new AuthenticationState(new ClaimsPrincipal(identity))));
        }

        public void Logout()
        {
            cachedUser = null;
            var user = new ClaimsPrincipal(new ClaimsIdentity());
            jsonRuntime.InvokeVoidAsync("sessionStorage.setItem", "currentUser", "");
            NotifyAuthenticationStateChanged(Task.FromResult(new AuthenticationState(user)));
        }

    }
}