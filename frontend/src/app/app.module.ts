import { SearchMagazineComponent } from './pages/user/home-user/search-magazine/search-magazine.component';
import { HeaderComponent } from './pages/header/header.component';
import { LoginControlComponent } from './pages/home/login-control/login-control.component';
import { SigninControlComponent } from './pages/home/signin-control/signin-control.component';
import { HomeComponent } from './pages/home/home.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomeUserComponent } from './pages/user/home-user/home-user.component';
import { ProfileComponent } from './pages/user/home-user/profile/profile.component';
import { HeaderEditorComponent } from './pages/user/home-user/header-editor/header-editor.component';
import { HomeAdminComponent } from './pages/user/home-admin/home-admin.component';
import { HeaderAdminComponent } from './pages/user/home-admin/header-admin/header-admin.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SigninControlComponent,
    LoginControlComponent,
    HeaderComponent,
    HomeUserComponent,
    ProfileComponent,
    HeaderEditorComponent,
    SearchMagazineComponent,
    HomeAdminComponent,
    HeaderAdminComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
