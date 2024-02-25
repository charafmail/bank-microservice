import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AccountsComponent} from "./ui/accounts/accounts.component";
import {CustomersComponent} from "./ui/customers/customers.component";
import {AuthGuard} from "./guards/auth.guard";


const routes: Routes = [
  { path : "customers" , component : CustomersComponent,canActivate:[AuthGuard],data:{role:'ADMIN'}},
  { path : "accounts", component : AccountsComponent,canActivate:[AuthGuard],data:{role:'ADMIN'}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
