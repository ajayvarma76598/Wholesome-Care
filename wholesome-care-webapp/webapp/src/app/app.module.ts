import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NutritionComponent } from './nutrition/nutrition.component';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatTableModule} from '@angular/material/table';
// import {MatRadioModule} from '@angular/material/radio';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import { PaymentComponent } from './payment/payment.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatListModule} from '@angular/material/list';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import { PricingComponent } from './pricing/pricing.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component'
import { HomeComponent } from './home/home.component';
import { BoardModeratorComponent } from './board-mentor/board-moderator.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
//import { ForgetComponent } from './forget/forget.component';
// import { ForgetComponent } from './forget/forget.component';
import { QuestionnaireComponent } from './questionnaire/questionnaire.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatRadioModule} from '@angular/material/radio';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { GuestDetailsComponent } from './guest-details/guest-details.component';
import { BMICalculatorComponent } from './bmicalculator/bmicalculator.component';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import {MatRippleModule} from '@angular/material/core';
import {MatGridListModule} from '@angular/material/grid-list';
import { ProfileComponent } from './profile/profile.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import{MatCarouselComponent, MatCarouselModule} from '@ngmodule/material-carousel';
import { SwiperModule } from 'swiper/angular';
// import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { MatSortModule } from '@angular/material/sort';
import { SamplePlanComponent } from './sample-plan/sample-plan.component';
import {MatTabsModule} from '@angular/material/tabs';
import { PlanViewComponent } from './plan-view/plan-view.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ListComponent } from './list/list.component';
import { FoodComponent } from './food/food.component';
import { FoodListComponent } from './food-list/food-list.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import { ActivityCardComponent } from './activity-card/activity-card.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { SentimentComponent } from './sentiment/sentiment.component';
import { BlogsComponent } from './blogs/blogs.component';
import { FooterComponent } from './footer/footer.component';
import { MentorsComponent } from './mentors/mentors.component';
import { CustomizedPlanComponent } from './customized-plan/customized-plan.component';
import {MatTreeModule} from '@angular/material/tree';
import { UserCardComponent } from './user-card/user-card.component';
import { ActivityContentComponent } from './activity-content/activity-content.component';
import { PositiveComponent } from './dialog/positive/positive.component';
import { NegativeComponent } from './dialog/negative/negative.component';
import { NeutralComponent } from './dialog/neutral/neutral.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { HelplineComponent } from './helpline/helpline.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { MealPlanComponent } from './meal-plan/meal-plan.component';
import { BeforeDashboardComponent } from './before-dashboard/before-dashboard.component';
import { MentorProfileComponent } from './mentor-profile/mentor-profile.component';
import { MentorDashboardComponent } from './mentor-dashboard/mentor-dashboard.component';
import { EditSlotsComponent } from './edit-slots/edit-slots.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { FollowNotificationComponent } from './follow-notification/follow-notification.component';
import {MatBadgeModule} from '@angular/material/badge';
import { ShowNotificationComponent } from './show-notification/show-notification.component';
import { DatePipe } from '@angular/common';
import { ConfirmBoxComponent } from './confirm-box/confirm-box.component';
import { BookingAppointmentComponent } from './booking-appointment/booking-appointment.component';
import { MentorProfileHeadComponent } from './mentor-profile-head/mentor-profile-head.component';
import { PageComponent } from './page/page.component';
import { UserComponent } from './dialog/user/user.component';
import { ChartsModule } from 'ng2-charts';
import { GoogleChartsModule } from 'angular-google-charts';
@NgModule({
  declarations: [
    AppComponent,
    NutritionComponent,
    PageComponent,
    PaymentComponent,
    NavBarComponent,
    BookingAppointmentComponent,
    MentorProfileHeadComponent,
    PricingComponent,
    ConfirmBoxComponent,
    LoginComponent,
    HomeComponent,
    BoardModeratorComponent,
    BoardUserComponent,
    QuestionnaireComponent,
    UserComponent,
   // ForgetComponent,
    RegisterComponent,
LandingPageComponent,
    GuestDetailsComponent,
    RegisterComponent,
    GuestDetailsComponent,
    BMICalculatorComponent,
    SamplePlanComponent,
    PlanViewComponent,
    SentimentComponent,
    PositiveComponent,
    NegativeComponent,
    NeutralComponent,
    DashboardComponent,
    ListComponent,
    FoodComponent,
    FoodListComponent,
    ActivityCardComponent,
    BlogsComponent,
    FooterComponent,
    MentorsComponent,
    CustomizedPlanComponent,
    UserCardComponent,
    ActivityContentComponent,
    UserProfileComponent,
    HelplineComponent,
    MealPlanComponent,
    BeforeDashboardComponent,
    MentorProfileComponent,
    MentorDashboardComponent,
    EditSlotsComponent,
    EditProfileComponent,
    FollowNotificationComponent,
    ShowNotificationComponent,
    // ConfirmBoxComponent,
    BookingAppointmentComponent,
    MentorProfileHeadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatFormFieldModule,
    DragDropModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatTableModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatListModule,
    FlexLayoutModule,
    MatDialogModule,
    MatProgressBarModule,
    MatRadioModule,
    MatCheckboxModule,
    MatRadioModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatOptionModule,
    MatSelectModule,
    MatCardModule,
    NgbModule,
    MatRippleModule,
    MatCardModule,
    MatGridListModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatCarouselModule,
    SwiperModule,
    MatSnackBarModule,
    // NgxMaterialTimepickerModule,
    MatTreeModule,
    MatSnackBarModule,
    MatBadgeModule,
    MatGridListModule,
    ChartsModule,
    GoogleChartsModule
  ],
  exports:[MatInputModule,
    MatButtonModule],
  providers: [authInterceptorProviders,
              DatePipe
            ],
  bootstrap: [AppComponent],
  entryComponents:[EditSlotsComponent,EditProfileComponent]
})
export class AppModule { }
