import { HomeAdminComponent } from './pages/user/home-admin/home-admin.component';
import { HomeUserComponent } from './pages/user/home-user/home-user.component';
import { HomeComponent } from './pages/home/home.component';

import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent
  },
  {
    path:'editor-home/:user',
    component:HomeUserComponent
  },
  {
    path:'admin-home/:user',
    component:HomeAdminComponent
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
