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
import { ProfileAdminComponent } from './pages/user/home-admin/profile-admin/profile-admin.component';
import { AdminsComponent } from './pages/user/home-admin/admins/admins.component';
import { MagazinesAdminComponent } from './pages/user/home-admin/magazines-admin/magazines-admin.component';
import { ReportAdminComponent } from './pages/user/home-admin/report-admin/report-admin.component';
import { CategoryComponent } from './pages/user/home-admin/profile-admin/category/category.component';
import { ImpuestoComponent } from './pages/user/home-admin/profile-admin/impuesto/impuesto.component';
import { TagComponent } from './pages/user/home-admin/profile-admin/tag/tag.component';
import {PdfViewerModule} from "ng2-pdf-viewer";
import { MyMagazinesComponent } from './pages/user/home-user/my-magazines/my-magazines.component';
import {AlifeFileToBase64Module} from "alife-file-to-base64";
import { MagazineDetailsComponent } from './pages/user/home-user/search-magazine/magazine-details/magazine-details.component';
import { GenerateSubscriptionComponent } from './pages/user/home-user/search-magazine/generate-subscription/generate-subscription.component';
import { MySubscriptionsComponent } from './pages/user/home-user/my-subscriptions/my-subscriptions.component';
import { AdComponent } from './pages/user/home-admin/profile-admin/ad/ad.component';
import { AdViewerComponent } from './pages/user/home-admin/profile-admin/ad/ad-viewer/ad-viewer.component';
import { EditorReportsComponent } from './pages/user/home-user/editor-reports/editor-reports.component';
import {NgxExtendedPdfViewerModule} from "ngx-extended-pdf-viewer";

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
    HeaderAdminComponent,
    ProfileAdminComponent,
    AdminsComponent,
    MagazinesAdminComponent,
    ReportAdminComponent,
    CategoryComponent,
    ImpuestoComponent,
    TagComponent,
    MyMagazinesComponent,
    MagazineDetailsComponent,
    GenerateSubscriptionComponent,
    MySubscriptionsComponent,
    AdComponent,
    AdViewerComponent,
    EditorReportsComponent
  ],
    imports: [
      BrowserModule,
      AppRoutingModule,
      FormsModule,
      ReactiveFormsModule,
      HttpClientModule,
      PdfViewerModule,
      AlifeFileToBase64Module,
      NgxExtendedPdfViewerModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
